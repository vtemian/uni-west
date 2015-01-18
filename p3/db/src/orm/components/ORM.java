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

    public Map<String, ArrayList<String>> getFieldsSQLStatements(){
        Map<String, ArrayList<String>> sqlFieldsStatements = new HashMap<String, ArrayList<String>>();

        for(Class<?> clazz: getClassesInPackage(modelsPackage)){
            Object instance = null;
            try {
                instance = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            ArrayList<String> sqlStatements = new ArrayList<String>();
            for(Field field : clazz.getDeclaredFields()) {
                try {
                    Class x = Class.forName(field.getType().getName());

                    for(Class inter: x.getInterfaces()){
                        if(inter.getName().equals("orm.fields.interfaces.IField")){
                            Method method = x.getDeclaredMethod("getSQLStatement");
                            String sqlStatement = (String) method.invoke(field.get(instance));
                            if(sqlStatement.contains("PRIMARY KEY")){
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

    public Set<Class> getClassesInPackage(String packageName) {
        Set<Class> classes = new HashSet<Class>();
        String packageNameSlashed = packageName.replace(".", "/");

        // Get a File object for the package
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
                // We are only interested in .class files
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

    @Override
    public Object retrieve(Integer entityID, Class<?> entity){
        String sqlStatement = "SELECT * FROM " + entity.getSimpleName() + " WHERE ID=" + entityID.toString() + ";";
        ResultSet result = dbConnection.select(sqlStatement);
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

            for(Field field : new_entity.getClass().getDeclaredFields()) {
                try {
                    Class<?> x = Class.forName(field.getType().getName());

                    for (Class inter : x.getInterfaces()) {
                        if (inter.getName().equals("orm.fields.interfaces.IField")) {
                            for (Method method : x.getDeclaredMethods()) {
                                if(method.getName().contains("setValue")){
                                    if(method.getParameterTypes().length != 2) continue;
                                    method.invoke(field.get(new_entity), result, field.getName());
                                }
                            }
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

    private Map<Field, Class<?>> getEntitiesFields(IEntity entity){
        Map<Field, Class<?>> fields = new HashMap<Field, Class<?>>();
        Class<?> clazz = entity.getClass();

        for(Field field : clazz.getDeclaredFields()) {
            try {
                Class x = Class.forName(field.getType().getName());

                for (Class inter : x.getInterfaces()) {
                    if (inter.getName().equals("orm.fields.interfaces.IField")) {
                        fields.put(field, x);
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return fields;
    }

    @Override
    public void create(IEntity entity) {
        int counter;
        ArrayList<String> values = new ArrayList<String>();
        String statement = "INSERT INTO " + entity.getClass().getSimpleName() + " VALUES ( ";

        for(Entry<Field, Class<?>> entry: getEntitiesFields(entity).entrySet()){
            try{
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

        counter = 0;
        for(String value: values) {
            if(counter > 0)
                statement += ",";

            statement += value;
            counter++;
        }

        statement += ");";
        dbConnection.executeSQL(statement, "UPDATE");
    }

    @Override
    public void update(IEntity entity) {
        int counter=0;
        Integer ID = new Integer(0);
        String statement = "UPDATE " + entity.getClass().getSimpleName() + " SET ";

        for(Entry<Field, Class<?>> entry: getEntitiesFields(entity).entrySet()){
            try {
                Method method = entry.getValue().getDeclaredMethod("getValue");
                String value = method.invoke(entry.getKey().get(entity)).toString();
                if(entry.getKey().getName().equals("ID")){
                    ID = Integer.parseInt(value);
                }else {
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

        if(counter > 0)
            statement += " WHERE ID=" + ID.toString();

        dbConnection.executeSQL(statement, "UPDATE");
    }

    @Override
    public void delete(IEntity entity) {
        Integer ID = new Integer(0);
        String statement = "DELETE FROM " + entity.getClass().getSimpleName() + " WHERE ";

        for(Entry<Field, Class<?>> entry: getEntitiesFields(entity).entrySet()){
            try{
                Method method = entry.getValue().getDeclaredMethod("getValue");
                String value = method.invoke(entry.getKey().get(entity)).toString();

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

        statement += " ID=" + ID.toString();
        dbConnection.executeSQL(statement, "UPDATE");
    }

    @Override
    public void setConnection(IConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
}
