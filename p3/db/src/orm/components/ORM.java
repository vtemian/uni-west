package orm.components;

import orm.connection.IConnection;
import orm.entity.IEntity;
import orm.fields.interfaces.IField;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
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

        System.out.println(createTable);
        //dbConnection.createTable(createTable);
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
                            sqlStatements.add(field.getName() + " " + sqlStatement);
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
    public void retrieve(Integer entityID, String entityClass) {

    }

    @Override
    public void create(IEntity entity) {

    }

    @Override
    public void update(IEntity entity) {

    }

    @Override
    public void delete(IEntity entity) {

    }

    @Override
    public void setConnection(IConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
}
