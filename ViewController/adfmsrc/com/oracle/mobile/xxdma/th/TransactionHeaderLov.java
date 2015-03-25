package com.oracle.mobile.xxdma.th;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.GenericType;
import oracle.adfmf.util.GenericVirtualType;

public class TransactionHeaderLov {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public TransactionHeaderLov() {
        super();
    }
    private String CallingSystemName;
    private String CallingInterfaceName;
    private String DebugFlag;
    private String UserId;
    private String RespId;
    private String RespAppId;
   
    public void setRespAppId(String RespAppId) {
        String oldRespAppId = this.RespAppId;
        this.RespAppId = RespAppId;
        propertyChangeSupport.firePropertyChange("RespAppId", oldRespAppId, RespAppId);
    }

    public String getRespAppId() {
        return RespAppId;
    }

    public void setRespId(String RespId) {
        String oldRespId = this.RespId;
        this.RespId = RespId;
        propertyChangeSupport.firePropertyChange("RespId", oldRespId, RespId);
    }

    public String getRespId() {
        return RespId;
    }

    
//    public GenericVirtualType getTransactionHeader() {
//    GenericVirtualType transactionHeader = new GenericVirtualType(null, "TransactionHeader");
//    transactionHeader.defineAttribute(null, "CallingInterfaceName", String.class, "1");
//    transactionHeader.defineAttribute(null, "CallingSystemName", String.class, "1");
//    transactionHeader.defineAttribute(null, "DebugFlag", String.class, "1");
//    transactionHeader.defineAttribute(null, "UserId", String.class, "1");
//    transactionHeader.defineAttribute(null, "respAppId", String.class, "1");
//    transactionHeader.defineAttribute(null, "ApplnId", String.class, "1");
//
//    //Fetching Status
//    GenericVirtualType status = new GenericVirtualType(null, "Status");
//    status.defineAttribute(null, "Code", String.class, "1");
//    status.defineAttribute(null, "Msg", String.class, "1");
//
//    transactionHeader.defineAttribute(null, "Status", GenericType.class, status);
//
//    return transactionHeader;
//    }

    public void setCallingSystemName(String CallingSystemName) {
        String oldCallingSystemName = this.CallingSystemName;
        this.CallingSystemName = CallingSystemName;
        propertyChangeSupport.firePropertyChange("CallingSystemName", oldCallingSystemName, CallingSystemName);
    }

    public String getCallingSystemName() {
        return CallingSystemName;
    }

    public void setCallingInterfaceName(String CallingInterfaceName) {
        String oldCallingInterfaceName = this.CallingInterfaceName;
        this.CallingInterfaceName = CallingInterfaceName;
        propertyChangeSupport.firePropertyChange("CallingInterfaceName", oldCallingInterfaceName, CallingInterfaceName);
    }

    public String getCallingInterfaceName() {
        return CallingInterfaceName;
    }

    public void setDebugFlag(String DebugFlag) {
        String oldDebugFlag = this.DebugFlag;
        this.DebugFlag = DebugFlag;
        propertyChangeSupport.firePropertyChange("DebugFlag", oldDebugFlag, DebugFlag);
    }

    public String getDebugFlag() {
        return DebugFlag;
    }

    public void setUserId(String UserId) {
        String oldUserId = this.UserId;
        this.UserId = UserId;
        propertyChangeSupport.firePropertyChange("UserId", oldUserId, UserId);
    }

    public String getUserId() {
        return UserId;
    }





    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
