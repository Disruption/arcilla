arcilla
=======

Lightweight HTTP server for quick REST API mocking using Java SE

The basic idea after arcilla is to be able to set up very quickly an http server that offers rest endpoints.

It is fairly easy to implement one in with Java Enterprise Edition, but it also usually takes more resources (Probably requiring you to use GlassFish or similar base servers), and is more convoluted for a first mocking phase.

It also serves for when you are developing the client side, and you know the specification of the remote API but it's not there yet, or you don't have access at the moment, or just you don't have internet available for whatever reason.

What it does:

- Allows you to have the http server running locally in 2 lines, plus one extra line for each REST endpoint you want to handle

- Very simple usage, as the handlers for petitions you must implement only require one method, which must return the proposed response, a small structure with the http return code you want to send, and the string containing the body you want to output)

- No big set ups. You can achieve the same this library does using other librarys, or basically using glassfish or other servers and one of the thousands of prebuilt projects that come with netbeans and such IDEs, but that implies a lot of code, and a big mess, just for some small prototyping, this library is just plug and play, you only write the important code, that is, the logic to prepare the response for every endpoint.

- Platform agnostic (thanks to java, of course :) ) and also, as it's a server, it's agnostic of the language used by the client, although clients based in java will be able to do more things, as they could just include the library in the project and use it from there reusing the data models and such things.

- As you are already serving the enpoints in a server, when you want to switch to the real API, you just need to switch the IP you are connecting too, that makes it very clean to work with, and also suitable for testing in a more decoupled way.

What it does NOT:

- It doesn't have logic to direct the same client to the same place to keep context, although you can implement any context logic you want in the handlers

- It's not ready for production environments, only prototyping, but it should let anybody build a client fast enough to switch afterwards to the real API.

