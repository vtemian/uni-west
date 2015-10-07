package tramways.application.importer.models;

import orm.entity.Entity;
import orm.fields.components.AutoIncrementField;
import orm.fields.components.CharField;
import orm.fields.components.IntegerField;

public class JunctionModel extends Entity{
    public AutoIncrementField ID = new AutoIncrementField();
    public IntegerField LineId = new IntegerField(0);
    public CharField LineNumber = new CharField("", 250);
    public IntegerField StationId = new IntegerField(0);
    public CharField RawStationName = new CharField("", 250);
    public CharField FriendlyStationName = new CharField("", 250);
    public CharField ShorStationName = new CharField("", 250);
    public CharField JunctionName = new CharField("", 250);
    public CharField Latitude = new CharField("", 250);
    public CharField Longitude = new CharField("", 250);

    public JunctionModel(){};
    public JunctionModel(String[] row){
        LineId.setValue(row[0]);
        LineNumber.setValue(row[1]);
        StationId.setValue(row[2]);
        RawStationName.setValue(row[3]);
        FriendlyStationName.setValue(row[4]);
        ShorStationName.setValue(row[5]);
        JunctionName.setValue(row[6]);
        Latitude.setValue(row[7]);
        Longitude.setValue(row[8]);
    }
}
