package gov.va.vba.vbms.common.utils;



import gov.va.vba.vbms.exceptions.VbmsException;
import gov.va.vba.vbms.exceptions.VbmsExceptionFlag;
import gov.va.vba.vbms.exceptions.VbmsFaultException;
import mockit.*;
import mockit.internal.util.StackTrace;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ErrorUtilTest {


    @NonStrict Logger logger;
    ErrorUtil instance = new ErrorUtil();


    @Before
    public void setup() {
        Deencapsulation.setField(instance, "skippedPackageNames", getSkippedPackageList());
        Deencapsulation.setField(instance, "filteredPackageNames", getFilteredPackagedList());
        Deencapsulation.setField(ErrorUtil.class, "LOGGER", logger);
        Deencapsulation.setField(ErrorUtil.class, "instance", instance);
    }

	@Test
	public void testGetViewName_FullPageNotUserError_ReturnsErrorPage() {
		Assert.assertEquals(ErrorUtil.ERROR_PAGE,ErrorUtil.getViewName(true,new Exception()));
	}

	@Test
	public void testGetViewName_NotFullPageNotUserError_ReturnsErrorTile() {
		Assert.assertEquals(ErrorUtil.ERROR_TILE,ErrorUtil.getViewName(false,new Exception()));
	}

	@Test
	public void testGetViewName_FullPageUserError_ReturnsUserErrorPage() {
		VbmsException vbmsException = new VbmsException("Test exception", VbmsExceptionFlag.USER_ERROR) {
            private static final long serialVersionUID = 1365723900988894302L;
        };
		Assert.assertEquals(ErrorUtil.USER_ERROR_PAGE,ErrorUtil.getViewName(true,vbmsException));
	}

	@Test
	public void testGetViewName_NotFullPageUserError_ReturnsUserErrorTile() {
		VbmsException vbmsException = new VbmsException("Test exception", VbmsExceptionFlag.USER_ERROR) {
            private static final long serialVersionUID = -5223894682907318197L;
        };
		Assert.assertEquals(ErrorUtil.USER_ERROR_TILE,ErrorUtil.getViewName(false,vbmsException));
	}

	@Test
	public void testValidationPage(@Mocked Logger logger,
	                          @Mocked Model model){
		String result = ErrorUtil.validationPage("message", logger, model);
		Assert.assertEquals(result, "../partials/errors/validation");
	}

    @Test
    public void testSessionUtil() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Constructor constructor = ErrorUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void addExceptionToModel_WillSetExceptionMessageAndType() {

        final String message = "Test exception message";
        final Exception ex = new Exception("123");

        Map<String, Object> model = new HashMap<String, Object>();

        Deencapsulation.setField(instance, "filteredStackTrace", "filtered");

        ErrorUtil.addExceptionToModel(message, ex, model, gov.va.vba.vbms.exceptions.VbmsExceptionFlag.WARNING);

        String expectedType = "warning";

        Assert.assertEquals(message, model.get("exceptionMessage"));
        Assert.assertEquals(expectedType, model.get("exceptionType"));
    }

    @Test
    public void addErrorExceptionToModel_WillSetExceptionMessageAndTypeAndGuid() {

        final String message = "Test exception message";
        final Exception ex = new Exception("123");

        Map<String, Object> model = new HashMap<String, Object>();

        Deencapsulation.setField(instance, "filteredStackTrace", "filtered");

        ErrorUtil.addExceptionToModel(message, ex, model, gov.va.vba.vbms.exceptions.VbmsExceptionFlag.ERROR);

        String expectedType = "error";

        Assert.assertEquals(message, model.get("exceptionMessage"));
        Assert.assertEquals(expectedType, model.get("exceptionType"));
        Assert.assertNotNull(model.get("exceptionIncidentId"));
    }

    @Test
    public void addExceptionToModel_WillLogException() {

        final String message = "Test exception message";
        final Exception ex = new Exception("123");

        Map<String, Object> model = new HashMap<String, Object>();

        new Expectations(ErrorUtil.class) {
            {
                ErrorUtil.log(anyString, message, ex);
            }
        };

        ErrorUtil.addExceptionToModel(message, ex, model, gov.va.vba.vbms.exceptions.VbmsExceptionFlag.WARNING);
    }

    @Test
    public void displayPage_WillReturnBlankPage_WhenWarning() {

        final String message = "Test exception message";
        final Exception ex = new Exception("123");

        Map<String, Object> model = new HashMap<String, Object>();

        Deencapsulation.setField(instance, "filteredStackTrace", "filtered");

        String expectedView = "errors/blank";
        String view = ErrorUtil.displayPage(message, ex, model, gov.va.vba.vbms.exceptions.VbmsExceptionFlag.WARNING);

        Assert.assertEquals(expectedView, view);
    }


    @Test
    public void displayPage_WillReturnBlankPage_WhenError() {

        final String message = "Test exception message";
        final Exception ex = new Exception("123");

        Map<String, Object> model = new HashMap<String, Object>();

        Deencapsulation.setField(instance, "filteredStackTrace", "filtered");

        // I know it is errors in the class - let's convert to error singular
        String expectedView = "errors/blank";
        String view = ErrorUtil.displayPage(message, ex, model, gov.va.vba.vbms.exceptions.VbmsExceptionFlag.ERROR);

        Assert.assertEquals(expectedView, view);
    }

    @Test
    public void displayPage_WillLogException() {

        final String message = "Test exception message";
        final Exception ex = new Exception("123");

        Map<String, Object> model = new HashMap<String, Object>();

        new Expectations(ErrorUtil.class) {
            {
                // No point in passing in the logger - can we remove that argument
                ErrorUtil.log(anyString, message, ex);
            }
        };

        ErrorUtil.displayPage(message, ex, model, gov.va.vba.vbms.exceptions.VbmsExceptionFlag.ERROR);
    }

    @Test
    public void FilterThrowable_WhenDelegatingFilterStackTrace_WillStop(@Mocked final PrintWriter pw) {
        final StackTraceElement[] trace = new StackTraceElement[2];

        trace[0] = new StackTraceElement("org.springframework.web.filter.DelegatingFilterProxy", "TestMethod", "TestFile", 100);
        trace[1] = new StackTraceElement("test.TestClass", "TestMethod", "TestFile", 100);

        final Exception cause = new Exception("456");
        cause.setStackTrace(trace);

        final Exception ex = new Exception("123", cause);
        ex.setStackTrace(trace);

        ErrorUtil util = new ErrorUtil();
        Deencapsulation.setField(util, "skippedPackageNames", getSkippedPackageList());
        Deencapsulation.setField(util, "filteredPackageNames", getFilteredPackagedList());
        ErrorUtil.FilteredThrowable ft = util.new FilteredThrowable(ex);

		Assert.assertEquals(0, ft.getSrc().getStackTrace().length);
        Assert.assertEquals(0, ft.getSrc().getCause().getStackTrace().length);
    }

    @Test
    public void FilterThrowable_WhenHttpServletService_WillStop(@Mocked final PrintWriter pw) {
        final StackTraceElement[] trace = new StackTraceElement[2];

        trace[0] = new StackTraceElement("javax.servlet.http.HttpServlet.service", "", "", 1);
        trace[1] = new StackTraceElement("test.TestClass", "", "", 1);

        final Exception cause = new Exception("456");
        cause.setStackTrace(trace);

        final Exception ex = new Exception("123", cause);
        ex.setStackTrace(trace);

        ErrorUtil util = new ErrorUtil();
        Deencapsulation.setField(util, "skippedPackageNames", getSkippedPackageList());
        Deencapsulation.setField(util, "filteredPackageNames", getFilteredPackagedList());
        ErrorUtil.FilteredThrowable ft = util.new FilteredThrowable(ex);

		Assert.assertEquals(0, ft.getSrc().getStackTrace().length);
        Assert.assertEquals(0, ft.getSrc().getCause().getStackTrace().length);
	}

    @Test
    public void FilterThrowable_WillFilterOutAOPFromExceptionStackTrace(@Mocked final PrintWriter pw) {

        final StackTraceElement[] trace = new StackTraceElement[15];

        trace[0] = new StackTraceElement("org.springframework.aop.Test1", "TestMethod", "TestFile", 100);
        trace[1] = new StackTraceElement("org.springframework.transaction.aspectj.Test1", "TestMethod", "TestFile", 100);
        trace[2] = new StackTraceElement("org.springframework.cache.aspectj.Test1", "TestMethod", "TestFile", 100);
        trace[3] = new StackTraceElement("org.springframework.transaction.interceptor.Test1", "TestMethod", "TestFile", 100);
        trace[4] = new StackTraceElement("org.springframework.cglib.proxy.MethodProxy.Test1", "TestMethod", "TestFile", 100);
        trace[5] = new StackTraceElement("sun.reflect.Test1", "TestMethod", "TestFile", 100);
        trace[6] = new StackTraceElement("java.lang.reflect.Test1", "TestMethod", "TestFile", 100);
        trace[7] = new StackTraceElement("$Proxy", "TestMethod", "TestFile", 100);
        trace[8] = new StackTraceElement("com.sun.proxy.$Proxy", "TestMethod", "TestFile", 100);
        trace[9] = new StackTraceElement("gov.va.vba.vbms.common.framework.performance", "TestMethod", "TestFile", 100);
        trace[10] = new StackTraceElement("gov.va.vba.vbms.common.framework.logging.Test1", "TestMethod", "TestFile", 100);
        trace[11] = new StackTraceElement("gov.va.vba.vbms.common.framework.auditing.Test1", "TestMethod", "TestFile", 100);
        trace[12] = new StackTraceElement("org.springframework.aop.Test1", "TestMethod", "TestFile", 100);
        trace[13] = new StackTraceElement("gov.va.vba.vbms.common.framework.exceptions.Test1", "TestMethod", "TestFile", 100);
        trace[14] = new StackTraceElement("test.TestClass", "TestMethod", "TestFile", 100);



        final Exception cause = new Exception("456");
        cause.setStackTrace(trace);

        final Exception ex = new Exception("123", cause);
        ex.setStackTrace(trace);

        ErrorUtil util = new ErrorUtil();
        Deencapsulation.setField(util, "skippedPackageNames", getSkippedPackageList());
        Deencapsulation.setField(util, "filteredPackageNames", getFilteredPackagedList());
        ErrorUtil.FilteredThrowable ft = util.new FilteredThrowable(ex);

        Assert.assertEquals(1, ft.getSrc().getStackTrace().length);
        Assert.assertEquals(1, ft.getSrc().getCause().getStackTrace().length);
        Assert.assertEquals(trace[14],ft.getSrc().getStackTrace()[0]);
        Assert.assertEquals(trace[14],ft.getSrc().getCause().getStackTrace()[0]);
    }

    @Test
    public void log_WhenUnfilteredLoggingSet_WillLogUnfiltered() {

        final Exception ex = new Exception("Test exception");
        Deencapsulation.setField(instance, "filteredStackTrace", "unfiltered");

        new Expectations() {{ logger.error(anyString, ex); times = 1; }};

        ErrorUtil.log("1234", "Test Message", ex);
    }


    @Test
    public void log_WhenFilteredLoggingSet_WillLogFiltered() {

        final Exception ex = new Exception("Test exception");

        Deencapsulation.setField(instance, "filteredStackTrace", "filtered");

        ErrorUtil.log("1234", "Test Message", ex);
    }


    @Test
    public void log_WhenBothLoggingSet_WillLogUnfiltered() {

        final Exception ex = new Exception("Test exception");

        Deencapsulation.setField(instance, "filteredStackTrace", "both");

        ErrorUtil.log("1234", "Test Message", ex);
    }

    @Test
    public void testIsRequestParameterLoggingRequired() {
        Assert.assertTrue(ErrorUtil.isRequestParameterLoggingRequired(new MissingServletRequestParameterException("test","test")));
        Assert.assertFalse(ErrorUtil.isRequestParameterLoggingRequired(new Exception("test")));
    }

    @Test
    public void testLogRequestParameters(@Mocked final HttpServletRequest request) {
        final Vector<String> paramNamesVector = new Vector<>();
        paramNamesVector.add("param1");
        paramNamesVector.add("param2");

        final String[] paramValues = {"value1", "value2"};

        new Expectations(){
            {
                request.getParameterNames();
                result = paramNamesVector.elements();

                request.getParameterValues("param1");
                result = null;

                request.getParameterValues("param2");

                result = paramValues;

                logger.error(anyString);
            }
        };

        ErrorUtil.logRequestParameters(request, "uid");
    }

    @Test
    public void testLogRequestParameters_exception(@Mocked final HttpServletRequest request) {
        final Vector<String> paramNamesVector = new Vector<>();
        paramNamesVector.add("param1");
        paramNamesVector.add("param2");

        final String[] paramValues = {"value1", "value2"};

        new Expectations(){
            {
                request.getParameterNames();
                result = new RuntimeException();

                logger.error(anyString,(RuntimeException)any);
            }
        };

        ErrorUtil.logRequestParameters(request, "uid");
    }

    private String getFilteredPackagedList() {
//        String filterePackages = "javax.servlet.http.HttpServlet.service,org.springframework,javax.servlet,sun.reflect,java.lang.reflect,$Proxy,com.sun.proxy.$Proxy,gov.va.vba.vbms.common.framework.performance,gov.va.vba.vbms.common.framework.logging,gov.va.vba.vbms.common.framework.auditing,gov.va.vba.vbms.common.framework.exceptions";
//        return Arrays.asList(filterePackages.split("\\s*,\\s*"));
        return "javax.servlet.http.HttpServlet.service,org.springframework,javax.servlet,sun.reflect,java.lang.reflect,$Proxy,com.sun.proxy.$Proxy,gov.va.vba.vbms.common.framework.performance,gov.va.vba.vbms.common.framework.logging,gov.va.vba.vbms.common.framework.auditing,gov.va.vba.vbms.common.framework.exceptions";
    }

    private String getSkippedPackageList() {
//        String skippedPackages = "javax.servlet.http.HttpServlet.service,org.springframework.web.filter.DelegatingFilterProxy";
//        return Arrays.asList(skippedPackages.split("\\s*,\\s*"));
        return "javax.servlet.http.HttpServlet.service,org.springframework.web.filter.DelegatingFilterProxy";
    }

    @Test
    public void testGetSourceFromStackTrace_whenExceptionCauseIsNull_returnSourceStr_VBMS() {
        Throwable throwable = new Throwable();
        final VbmsFaultException  faultException = null;
        throwable.initCause(faultException);
        final Exception exception = new VbmsFaultException("This is a BGS error", faultException);

        new NonStrictExpectations() {
            {
                //nothing
            }
        };

        String sourceStr = Deencapsulation.invoke(ErrorUtil.class, "getSourceFromStackTrace", exception);
        Assert.assertNotNull(sourceStr);
        Assert.assertTrue(sourceStr.contains(VbmsExceptionFlag.VBMS.toString()));
    }

    @Test
    public void testGetSourceFromStackTrace_returnSourceStr() {
        Throwable throwable = new Throwable();
        final VbmsFaultException  faultException = new VbmsFaultException("this is a bgs error");
        faultException.setFaultSystem(VbmsExceptionFlag.BGS);
        throwable.initCause(faultException);
        final Exception exception = new VbmsFaultException("This is a BGS error", faultException);

        new NonStrictExpectations() {
            {
                //nothing
            }
        };

        String sourceStr = Deencapsulation.invoke(ErrorUtil.class, "getSourceFromStackTrace", exception);
        Assert.assertNotNull(sourceStr);
    }
}
