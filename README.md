EasyRestClient
=========

The goal of EasyRestClient is to enable developers to easily make http requests.
Here are some of the features that EasyRestClient provides :

* Standardized mechanism to make service calls and receive the responses
* Supports Delete requests with an entity
* Supports Multiple request formats i.e Json, FormData, MultiPart
* Supports Easy secure connections with self signed certificates using Bouncy Castle Keystore 
* Extensive logging of requests and responses as well as informative error responses
* Ease of control over http connection parameters.

All Requests follow a common set of steps

Ex API http://api.xyz.com/path/method-name
```Java
BASE_URL = api.xyz.com
// Initialize Easy Droid in your application oncreate
EasyDroid.init(BASE_URL);
// Create a request object
EasyJsonServiceRequest easyJsonServiceRequest = new EasyJsonServiceRequest(Request, RequestMethod.POST, context);
// Assign the method and path of the service
easyJsonServiceRequest.setMethod("Service-Method-Name");
easyJsonServiceRequest.setPath("Service-Path-Name");
// Assign a ResponseContainer to hold the response and a request code to differentiate between multiple requests in the callback
easyJsonServiceRequest.setRequestCode(GET_SERVICE_REQUESTCODE);
easyJsonServiceRequest.setResponsibleClass(ResponseContainer.class);
// Spawn a service thread to perform the network call
EasyServiceThread easyServiceThread = new EasyServiceThread(onEasyServiceCompleteListener, showProgressDialog, context);
easyServiceThread.execute(easyJsonServiceRequest);
```


