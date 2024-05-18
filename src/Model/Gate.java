package Model;


import java.util.Date;

public class Gate extends BaseModel{
    private GateType gateType;
    private Operations operations;
    private String name;

    public Gate(GateType gateType, Operations operations, String name) {
        this.gateType = gateType;
        this.operations = operations;
        this.name = name;
    }

    public GateType getGateType() {
        return gateType;
    }

    public void setGateType(GateType gateType) {
        this.gateType = gateType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Operations getOperations() {
        return operations;
    }

    public void setOperations(Operations operations) {
        this.operations = operations;
    }
}
