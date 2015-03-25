package com.oracle.mobile.xxdma.th;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class WSPayload {
    private TransactionHeaderLov TransactionHeader;

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public WSPayload() {
        super();
    }

    public void setTransactionHeader(TransactionHeaderLov TransactionHeader) {
        TransactionHeaderLov oldTransactionHeader = this.TransactionHeader;
        this.TransactionHeader = TransactionHeader;
        propertyChangeSupport.firePropertyChange("TransactionHeader", oldTransactionHeader, TransactionHeader);
    }

    public TransactionHeaderLov getTransactionHeader() {
        return TransactionHeader;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
