interface IEntityWithName {
    String name;
    public void getName(){}
    public default void setName(String name){}
}
