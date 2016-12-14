package com.example.thisi.applicationx;

//------------------------------------------------------------------------------
// <wsdl2code-generated>
//    This code was generated by http://www.wsdl2code.com version  2.6
//
// Date Of Creation: 10/29/2016 5:45:32 PM
//    Please dont change this code, regeneration will override your changes
//</wsdl2code-generated>
//
//------------------------------------------------------------------------------
//
//This source code was auto-generated by Wsdl2Code  Version
//

import java.util.List;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.HeaderProperty;
import java.util.Hashtable;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import android.os.AsyncTask;

import com.example.thisi.applicationx.IWsdl2CodeEvents;
import com.example.thisi.applicationx.WS_Enums;

import org.ksoap2.serialization.MarshalFloat;

public class Service1 {

    public String NAMESPACE ="http://tempuri.org/";
    public String url="";
    public int timeOut = 10000;
    public IWsdl2CodeEvents eventHandler;
    public WS_Enums.SoapProtocolVersion soapVersion;

    public Service1(){}

    public Service1(IWsdl2CodeEvents eventHandler)
    {
        this.eventHandler = eventHandler;
    }
    public Service1(IWsdl2CodeEvents eventHandler,String url)
    {
        this.eventHandler = eventHandler;
        this.url = url;
    }
    public Service1(IWsdl2CodeEvents eventHandler,String url,int timeOutInSeconds)
    {
        this.eventHandler = eventHandler;
        this.url = url;
        this.setTimeOut(timeOutInSeconds);
    }
    public void setTimeOut(int seconds){
        this.timeOut = seconds * 1000;
    }
    public void setUrl(String url){
        this.url = url;
    }


    public void SQLExecScalarAsync(String sQL) throws Exception{
        if (this.eventHandler == null)
            throw new Exception("Async Methods Requires IWsdl2CodeEvents");
        SQLExecScalarAsync(sQL, null);
    }

    public void SQLExecScalarAsync(final String sQL,final List<HeaderProperty> headers) throws Exception{

        new AsyncTask<Void, Void, String>(){
            @Override
            protected void onPreExecute() {
                eventHandler.Wsdl2CodeStartedRequest();
            }

            @Override
            protected String doInBackground(Void... params) {
                return SQLExecScalar(sQL, headers);
            }
            @Override
            protected void onPostExecute(String result)
            {
                eventHandler.Wsdl2CodeEndedRequest();
                if (result != null){
                    eventHandler.Wsdl2CodeFinished("SQLExecScalar", result);
                }
            }
        }.execute();
    }

    public String SQLExecScalar(String sQL){
        return SQLExecScalar(sQL, null);
    }

    public String SQLExecScalar(String sQL,List<HeaderProperty> headers){
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("http://tempuri.org/","SQLExecScalar");
        soapReq.addProperty("SQL",sQL);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url,timeOut);
        try{
            if (headers!=null){
                httpTransport.call("http://tempuri.org/IService1/SQLExecScalar", soapEnvelope,headers);
            }else{
                httpTransport.call("http://tempuri.org/IService1/SQLExecScalar", soapEnvelope);
            }
            Object retObj = soapEnvelope.bodyIn;
            if (retObj instanceof SoapFault){
                SoapFault fault = (SoapFault)retObj;
                Exception ex = new Exception(fault.faultstring);
                if (eventHandler != null)
                    eventHandler.Wsdl2CodeFinishedWithException(ex);
            }else{
                SoapObject result=(SoapObject)retObj;
                if (result.getPropertyCount() > 0){
                    Object obj = result.getProperty(0);
                    if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                        SoapPrimitive j =(SoapPrimitive) obj;
                        String resultVariable = j.toString();
                        return resultVariable;
                    }else if (obj!= null && obj instanceof String){
                        String resultVariable = (String) obj;
                        return resultVariable;
                    }
                }
            }
        }catch (Exception e) {
            if (eventHandler != null)
                eventHandler.Wsdl2CodeFinishedWithException(e);
            e.printStackTrace();
        }
        return "";
    }

    public void SQLExecAsync(String sQL) throws Exception{
        if (this.eventHandler == null)
            throw new Exception("Async Methods Requires IWsdl2CodeEvents");
        SQLExecAsync(sQL, null);
    }

    public void SQLExecAsync(final String sQL,final List<HeaderProperty> headers) throws Exception{

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPreExecute() {
                eventHandler.Wsdl2CodeStartedRequest();
            }

            @Override
            protected Void doInBackground(Void... params) {
                SQLExec(sQL, headers);
                return null;
            }
            @Override
            protected void onPostExecute(Void result)
            {
                eventHandler.Wsdl2CodeEndedRequest();
                if (result != null){
                    eventHandler.Wsdl2CodeFinished("SQLExec", result);
                }
            }
        }.execute();
    }

    public void SQLExec(String sQL){
        SQLExec(sQL, null);
    }

    public void SQLExec(String sQL,List<HeaderProperty> headers){
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("http://tempuri.org/","SQLExec");
        soapReq.addProperty("SQL",sQL);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url,timeOut);
        try{
            if (headers!=null){
                httpTransport.call("http://tempuri.org/IService1/SQLExec", soapEnvelope,headers);
            }else{
                httpTransport.call("http://tempuri.org/IService1/SQLExec", soapEnvelope);
            }
        }catch (Exception e) {
            if (eventHandler != null)
                eventHandler.Wsdl2CodeFinishedWithException(e);
            e.printStackTrace();
        }
    }

    public void SQLResultAsync(String sQL) throws Exception{
        if (this.eventHandler == null)
            throw new Exception("Async Methods Requires IWsdl2CodeEvents");
        SQLResultAsync(sQL, null);
    }

    public void SQLResultAsync(final String sQL,final List<HeaderProperty> headers) throws Exception{

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPreExecute() {
                eventHandler.Wsdl2CodeStartedRequest();
            }

            @Override
            protected Void doInBackground(Void... params) {
                SQLResult(sQL, headers);
                return null;
            }
            @Override
            protected void onPostExecute(Void result)
            {
                eventHandler.Wsdl2CodeEndedRequest();
                if (result != null){
                    eventHandler.Wsdl2CodeFinished("SQLResult", result);
                }
            }
        }.execute();
    }

    public void SQLResult(String sQL){
        SQLResult(sQL, null);
    }

    public void SQLResult(String sQL,List<HeaderProperty> headers){
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("http://tempuri.org/","SQLResult");
        soapReq.addProperty("SQL",sQL);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url,timeOut);
        try{
            if (headers!=null){
                httpTransport.call("http://tempuri.org/IService1/SQLResult", soapEnvelope,headers);
            }else{
                httpTransport.call("http://tempuri.org/IService1/SQLResult", soapEnvelope);
            }
        }catch (Exception e) {
            if (eventHandler != null)
                eventHandler.Wsdl2CodeFinishedWithException(e);
            e.printStackTrace();
        }
    }

    public SoapObject SQLResultReturn(String sQL,List<HeaderProperty> headers){
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("http://tempuri.org/","SQLResult");
        soapReq.addProperty("SQL",sQL);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url,timeOut);
        try{
            if (headers!=null){
                httpTransport.call("http://tempuri.org/IService1/SQLResult", soapEnvelope,headers);
                SoapObject respo = (SoapObject)soapEnvelope.getResponse();

                if(respo != null && respo.getPropertyCount() > 0) {
                    SoapObject diffgram = (SoapObject) respo.getProperty(1);
                    SoapObject datasetRespo = (SoapObject) diffgram.getProperty("NewDataSet");

                    return datasetRespo;
                }
            }else{
                httpTransport.call("http://tempuri.org/IService1/SQLResult", soapEnvelope);
                SoapObject respo = (SoapObject)soapEnvelope.getResponse();

                if(respo != null && respo.getPropertyCount() > 0) {
                    SoapObject diffgram = (SoapObject) respo.getProperty(1);
                    SoapObject datasetRespo = (SoapObject) diffgram.getProperty("NewDataSet");

                    return datasetRespo;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
