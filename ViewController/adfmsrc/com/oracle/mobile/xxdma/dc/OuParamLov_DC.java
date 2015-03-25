package com.oracle.mobile.xxdma.dc;

import com.oracle.mobile.xxdma.th.TransactionHeaderLov;

import com.oracle.mobile.xxdma.th.WSPayload;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.framework.model.AdfELContext;
import oracle.adfmf.util.GenericType;

public class OuParamLov_DC {
    public OuParamLov_DC() {
        super();
    }
    private static List s_ouList = new ArrayList();
    String OuList[] = null;

    public void AddOU(String l) {
        s_ouList.add(l);
        System.out.println("String Added to List s_ouList" + l);
    }

    public String[] getOuList() {
        if (OuList == null) {
            try {
                System.out.println("Calling ProcessOU");
//                AddOU("Select");
                ProcessOU();
            } catch (AdfInvocationException ex) {

                AdfException e = new AdfException("Error Invoking getOuList", AdfException.WARNING);
                System.out.println("Error Invoking getOuList");

            }
            OuList = (String[]) s_ouList.toArray(new String[s_ouList.size()]);
            System.out.println("getOuList complete");
        }

        System.out.println("Inside getOuList");

        return OuList;
    }

    public void ProcessOU() throws AdfInvocationException {


        List pnames = new ArrayList();
        List pvals = new ArrayList();
        List ptypes = new ArrayList();
        GenericType resultGT = null;
        ValueExpression ve = null;
        TransactionHeaderLov payloadTH = new TransactionHeaderLov();
        WSPayload payload = new WSPayload();

        payloadTH.setCallingSystemName("MAF");
        payloadTH.setCallingInterfaceName("GL");
        payloadTH.setDebugFlag("Y");
        //
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.UserId}", String.class);
        String userId = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        payloadTH.setUserId(userId);

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.RespId}", String.class);
        String respId = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        payloadTH.setRespId(respId);

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ApplnId}", String.class);
        String applnId = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        payloadTH.setRespAppId(applnId);
        //        payloadTH.setUserId("1261");
        //        payloadTH.setRespId("50637");
        //        payloadTH.setRespAppId("101");
        //
        payload.setTransactionHeader(payloadTH);

        GenericType payloadGT =
            GenericTypeBeanSerializationHelper.toGenericType("OuParamLov_WL.Types.process.TransactionHeader",
                                                             payloadTH);

        pnames.add("TransactionHeader");
        ptypes.add(GenericType.class);
        pvals.add(payloadGT);

        try {
            resultGT =
                (GenericType) AdfmfJavaUtilities.invokeDataControlMethod("OuParamLov_WL", null, "process", pnames,
                                                                         pvals, ptypes);
//            resultGT = resultGT.getParent();
            Object provider = AdfmfJavaUtilities.getDataControlProvider("OuParamLov_WL");
            GenericTypeBeanSerializationHelper.fromGenericType(provider, resultGT);

                        for (int i = 0; i < (resultGT.getAttributeCount() - 1); i++) {
                            GenericType entityGenericType = (GenericType) resultGT.getAttribute(i);
                            AddOU(entityGenericType.getAttribute(0).toString());
//                            GenericType entityGenericType1 = (GenericType) entityGenericType.getAttribute(0);
//                            for(int j = 0; j< entityGenericType1.getAttributeCount(); j++) {
//            
//                                 Object OuName =entityGenericType1.getAttribute(j);
//                                AddOU(OuName.toString());
//            
//                            }
//            
                        }
//            for (int i = 0; i < resultGT.getAttributeCount(); i++) {
//                GenericType entityGenericType = (GenericType) resultGT.getAttribute(i);
//                GenericType entityGenericType1 = (GenericType) entityGenericType.getAttribute(j);
//                for (int j = 0; j < entityGenericType.getAttributeCount(); j++) {
//                  
//                    Object OuName = entityGenericType1.getAttribute(0);
//                    AddOU(OuName.toString());
//                }
//            }

        } catch (AdfInvocationException ex) {

            AdfException e = new AdfException("Error Invoking ProcessOU", AdfException.WARNING);
            System.out.println("Error Invoking ProcessOU");
        }
    }
}

//        System.out.println("Start ProcessOU");
//        pnames.add("CallingSystemName");
//        ptypes.add(String.class);
//        pvals.add("MAF");
//        pnames.add("CallingInterfaceName");
//        ptypes.add(String.class);
//        pvals.add("PO");
//        pnames.add("DebugFlag");
//        ptypes.add(String.class);
//        pvals.add("Y");
//        pnames.add("userId");
//        ptypes.add(String.class);
//        pvals.add("1261");
//        pnames.add("respId");
//        ptypes.add(String.class);
//        pvals.add("50637");
//        pnames.add("applId");
//        ptypes.add(String.class);
//        pvals.add("101");

