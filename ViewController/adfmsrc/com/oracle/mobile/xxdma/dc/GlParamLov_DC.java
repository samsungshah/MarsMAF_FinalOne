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

public class GlParamLov_DC {
    public GlParamLov_DC() {
        super();
    }
    private static List s_periodList = new ArrayList();
    String PeriodList[] = null;

    public void AddOU(String l) {
        s_periodList.add(l);
        System.out.println("String Added to List s_ouList" + l);
    }

    public String[] getPeriodList() {
            try {
                System.out.println("Calling ProcessOU");
                if (PeriodList == null) {
//                AddOU("Select");
                ProcessOU();
                }
            } catch (AdfInvocationException ex) {

                AdfException e = new AdfException("Error Invoking getOuList", AdfException.WARNING);
                System.out.println("Error Invoking getOuList");
            }
        PeriodList = (String[]) s_periodList.toArray(new String[s_periodList.size()]);
        System.out.println("getOuList complete");

        System.out.println("Inside getOuList");

        return PeriodList;
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

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.UserId}", String.class);
        String userId = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        payloadTH.setUserId(userId);

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.RespId}", String.class);
        String respId = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        payloadTH.setRespId(respId);

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.ApplnId}", String.class);
        String applnId = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        payloadTH.setRespAppId(applnId);

        payload.setTransactionHeader(payloadTH);

        GenericType payloadGT =
            GenericTypeBeanSerializationHelper.toGenericType("OuParamLov_WL.Types.process.TransactionHeader",
                                                             payloadTH);
        pnames.add("TransactionHeader");
        ptypes.add(GenericType.class);
        pvals.add(payloadGT);

        try {
            resultGT =
                (GenericType) AdfmfJavaUtilities.invokeDataControlMethod("GlParamLov_WL", null, "process", pnames,
                                                                         pvals, ptypes);
//            resultGT = resultGT.getParent();
            Object provider = AdfmfJavaUtilities.getDataControlProvider("GlParamLov_WL");
            GenericTypeBeanSerializationHelper.fromGenericType(provider, resultGT);

            for (int i = 0; i < (resultGT.getAttributeCount() - 1); i++) {
                GenericType entityGenericType = (GenericType) resultGT.getAttribute(i);
            AddOU(entityGenericType.getAttribute(0).toString());
        }
        } catch (AdfInvocationException ex) {
            AdfException e = new AdfException("Error Invoking ProcessOU", AdfException.WARNING);
            System.out.println("Error Invoking ProcessOU");
        }
    }
}
