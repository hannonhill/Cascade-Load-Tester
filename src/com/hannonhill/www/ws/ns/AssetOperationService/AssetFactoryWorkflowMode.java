/**
 * AssetFactoryWorkflowMode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 12, 2007 (02:39:05 EDT) WSDL2Java emitter.
 */

package com.hannonhill.www.ws.ns.AssetOperationService;

@SuppressWarnings({"serial", "unchecked"}) public class AssetFactoryWorkflowMode implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected AssetFactoryWorkflowMode(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _value1 = "folder-controlled";
    public static final java.lang.String _value2 = "factory-controlled";
    public static final java.lang.String _value3 = "none";
    public static final AssetFactoryWorkflowMode value1 = new AssetFactoryWorkflowMode(_value1);
    public static final AssetFactoryWorkflowMode value2 = new AssetFactoryWorkflowMode(_value2);
    public static final AssetFactoryWorkflowMode value3 = new AssetFactoryWorkflowMode(_value3);
    public java.lang.String getValue() { return _value_;}
    public static AssetFactoryWorkflowMode fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        AssetFactoryWorkflowMode enumeration = (AssetFactoryWorkflowMode)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static AssetFactoryWorkflowMode fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssetFactoryWorkflowMode.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.hannonhill.com/ws/ns/AssetOperationService", "asset-factory-workflow-mode"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
