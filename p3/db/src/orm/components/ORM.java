package orm.components;

import orm.connection.IConnection;
import orm.entity.IEntity;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map.Entry;

public class ORM implements IORM{
    private String modelsPackage;
    private IConnection dbConnection;

    public ORM(IConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public ORM(String modelsPackage, IConnection dbConnection) {
        this.modelsPackage = modelsPackage;
        this.dbConnection = dbConnection;
    }

    /**
     * Create the tables interpreting defined models in modelsPackage
     */
    @Override
    public void sync() {
        String createTable = "";

        for(Entry<String, ArrayList<String>> entry: getFieldsSQLStatements().entrySet()){
            createTable += "CREATE TABLE IF NOT EXISTS " + entry.getKey() + " (";

            int counter = 0;
            for(String statement: entry.getValue()){
                if(counter > 0)
                    createTable += ",";
                createTable += statement;
                counter++;
            }
            createTable += ");";
        }

        dbConnection.createTable(createTable);
    }

    /**
     * From modelsPackage, get all the classes, and for each classes get their fields.
     * Each field should have a sqlStatement which will indicate it's type(varchar, int etc.)
     *
     * In order to create the table, we need to get those sqlStatements.
     *
     * @return Map: field name -> sql statement
     */
    public Map<String, ArrayList<String>> getFieldsSQLStatements(){
        Map<String, ArrayList<String>> sqlFieldsStatements = new HashMap<String, ArrayList<String>>();

        // get all classes from the models package (entities)
        for(Class<?> clazz: getClassesInPackage(modelsPackage)){
            Object instance = null;
            try {
                // create a new instance for that class
                instance = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            ArrayList<String> sqlStatements = new ArrayList<String>();

            // get all the fields of a given entity
            for(Field field : clazz.getDeclaredFields()) {
                try {
                    Class iField = Class.forName(field.getType().getName());
                    // check to see if a field implements IField
                    for(Class inter: iField.getInterfaces()){
                        if(inter.getName().equals("orm.fields.interfaces.IField")){
                            // get the method call "getSQLStatement"
                            Method method = iField.getDeclaredMethod("getSQLStatement");
                            String sqlStatement = (String) method.invoke(field.get(instance));
                            // if the statement contains "PRIMARY KEY" we need to add also the field name
                            if(sqlStatement.contains("PRIMARY KEY")){
                                // ID int not null, PRIMARY KEY (ID)
                                sqlStatements.add(field.getName() + " " + sqlStatement + field.getName()+ ")");
                            }else{
                                sqlStatements.add(field.getName() + " " + sqlStatement);
                            }
                            break;
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            sqlFieldsStatements.put(clazz.getSimpleName(), sqlStatements);
        }
        return sqlFieldsStatements;
    }

    /**
     *  Given a certain packageName (eg: a.b.c) it get all the classes from that package
     * @param packageName: String representing a path to modelsPackage
     * @return Set: a set of classes which contains all the entities classes
     */
    public Set<Class> getClassesInPackage(String packageName) {
        Set<Class> classes = new HashSet<Class>();
        String packageNameSlashed = packageName.replace(".", "/");

        // get a File object for the package
        URL directoryURL = Thread.currentThread().getContextClassLoader().getResource(packageNameSlashed);
        if (directoryURL == null) {
            return classes;
        }

        String directoryString = directoryURL.getFile();
        if (directoryString == null) {
            return classes;
        }

        File directory = new File(directoryString);
        if (directory.exists()) {
            // Get the list of the files contained in the package
            String[] files = directory.list();
            for (String fileName : files) {
                fileName = fileName.substring(0, fileName.length() - 6);
                try {
                    classes.add(Class.forName(packageName + "." + fileName));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return classes;
    }

    /**
     *  Builds a select query, retrieving an entity by an unique identifier
     * @param entityID: int representing the unique identifier of the entity
     * @param entity: the entity class
     * @return a new entity representing the row with the given ID
     */
    @Override
    public Object retrieve(Integer entityID, Class<?> entity){
        // creating and executing the query
        String sqlStatement = "SELECT * FROM " + entity.getSimpleName() + " WHERE ID=" + entityID.toString() + ";";
        ResultSet result = dbConnection.select(sqlStatement);

        // create a new entity instance
        Object new_entity = null;
        try {
            new_entity = entity.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if(result == null){
            return null;
        }

        try {
            if(!result.next()) {
                return null;
            }

            assert new_entity != null;
            // get all the fields from the entity
            for(Field field : new_entity.getClass().getDeclaredFields()) {
                try {
                    Class<?> x = Class.forName(field.getType().getName());
                    for (Class inter : x.getInterfaces()) {
                        // check to see if the field is an IField
                        if (inter.getName().equals("orm.fields.interfaces.IField")) {
                            for (Method method : x.getDeclaredMethods()) {
                                // look for setValue method
                                if(method.getName().contains("setValue")){
                                    // check if is the right setValue method
                                    if(method.getParameterTypes().length != 2) continue;
                                    // set the value from query result into the field
                                    method.invoke(field.get(new_entity), result, field.getName());
                                }
                            }
                            break;
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new_entity;
    }

    /**
     *  Helper method that given a certain entity it will return all it's fields.
     *  It uses the Reflection API.
     * @param entity
     * @return Map: Field -> Class
     */
    private Map<Field, Class<?>> getEntitiesFields(IEntity entity){
        Map<Field, Class<?>> fields = new HashMap<Field, Class<?>>();
        // get Entity class
        Class<?> clazz = entity.getClass();

        // retrieve all the fields from Entity class
        for(Field field : clazz.getDeclaredFields()) {
            try {
                Class iField = Class.forName(field.getType().getName());
                // check to see if the field is implementing IField interface
                for (Class inter : iField.getInterfaces()) {
                    if (inter.getName().equals("orm.fields.interfaces.IField")) {
                        fields.put(field, iField);
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return fields;
    }

    /**
     * Given a certain entity, it builds the query used to insert that entity in the corect table
     * @param entity
     */
    @Override
    public void create(IEntity entity) {
        int counter;
        ArrayList<String> values = new ArrayList<String>();
        String statement = "INSERT INTO " + entity.getClass().getSimpleName() + " VALUES ( ";

        // get all the fields
        for(Entry<Field, Class<?>> entry: getEntitiesFields(entity).entrySet()){
            try{
                // retrieve the value of a certain field
                Method method = entry.getValue().getDeclaredMethod("getValue");
                String value = method.invoke(entry.getKey().get(entity)).toString();
                values.add(value);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // builds the query with the given values
        counter = 0;
        for(String value: values) {
            if(counter > 0)
                statement += ",";

            statement += value;
            counter++;
        }
        statement += ");";

        // execute the query
        dbConnection.executeSQL(statement, "UPDATE");
    }

    /**
     *  Given a certain entity, it will update its content in db
     * @param entity
     */
    @Override
    public void update(IEntity entity) {
        int counter=0;
        Integer ID = 0;
        String statement = "UPDATE " + entity.getClass().getSimpleName() + " SET ";

        // retrieve the fields
        for(Entry<Field, Class<?>> entry: getEntitiesFields(entity).entrySet()){
            try {
                // get the value of a field
                Method method = entry.getValue().getDeclaredMethod("getValue");
                String value = method.invoke(entry.getKey().get(entity)).toString();
                // we need to get the correct id in order to update a row
                if(entry.getKey().getName().equals("ID")){
                    ID = Integer.parseInt(value);
                }else {
                    // build the query
                    if (counter > 0)
                        statement += ",";
                    statement += entry.getKey().getName() + "=" + value;
                    counter++;
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // finish query
        if(counter > 0)
            statement += " WHERE ID=" + ID.toString();

        // do the actual update
        dbConnection.executeSQL(statement, "UPDATE");
    }

    /**
     *  Delete the content of a given entity from db
     * @param entity
     */
    @Override
    public void delete(IEntity entity) {
        Integer ID = 0;
        String statement = "DELETE FROM " + entity.getClass().getSimpleName() + " WHERE ";

        // get the fields
        for(Entry<Field, Class<?>> entry: getEntitiesFields(entity).entrySet()){
            try{
                Method method = entry.getValue().getDeclaredMethod("getValue");
                String value = method.invoke(entry.getKey().get(entity)).toString();

                // search for ID
                if(entry.getValue().getName().equals("ID")){
                    ID = Integer.parseInt(value);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // build the query
        statement += " ID=" + ID.toString();
        // execute query
        dbConnection.executeSQL(statement, "UPDATE");
    }

    @Override
    public void setConnection(IConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
}
