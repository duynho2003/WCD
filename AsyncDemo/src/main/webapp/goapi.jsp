<%-- Document : home Created on : Jun 7, 2023, 10:54:13 AM Author : trungtruong
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>JSP Page</title>
  </head>
  <body>
    <h1>Hello World!</h1>
    <h1 id="hello"></h1>
    <input
      type="button"
      value="Update XMLHttpRequest"
      onclick="makeRequest()"
    />
    <input type="button" value="Update AJAX" id="REST-button" />

    <script
      src="https://code.jquery.com/jquery-3.1.0.js"
      integrity="sha256-slogkvB1K3VOkzAI8QITxV3VzpOnkeNVsKvtkYLMjfk="
      crossorigin="anonymous"
    ></script>
    <script type="text/javascript">
      function getXMLHttpRequest() {
        var xmlHttpReq = false;
        // Creating XMLHttpRequest object for non-Microsoft
        //browsers
        if (window.XMLHttpRequest) {
          xmlHttpReq = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
          try {
            // Creating XMLHttpRequest object in later versions
            // of Internet Explorer
            xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
          } catch (exp1) {
            try {
              // to create XMLHttpRequest object in older versions
              // of Internet Explorer
              xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (exp2) {
              xmlHttpReq = false;
            }
          }
        }
        return xmlHttpReq;
      }
      /* AJAX call starts with this function*/
      function makeRequest() {
        console.log("make request");
        var xmlHttpRequest = getXMLHttpRequest();
        xmlHttpRequest.onreadystatechange =
          getReadyStateHandler(xmlHttpRequest);
        xmlHttpRequest.open("GET", "helloapi", true);
        xmlHttpRequest.send();
      }

      /* Returns a function that waits for the state change in
		 XMLHttpRequest*/
      function getReadyStateHandler(xmlHttpRequest) {
        // an anonymous function returned
        // it listens to the XMLHttpRequest instance
        return function () {
          if (xmlHttpRequest.readyState === 4) {
            if (xmlHttpRequest.status === 200) {
              document.getElementById("hello").innerHTML =
                xmlHttpRequest.responseText;
            } else {
              alert(
                "HTTP error " +
                  xmlHttpRequest.status +
                  ": " +
                  xmlHttpRequest.statusText
              );
            }
          }
        };
      }
    </script>
    <script>
      function getData(data) {
    		console.log("-----> " + data);
    		document.getElementById("hello").innerHTML = "<b>ID:</b> " + data.id + "<br/>" + "<b>NAME:</b> " + data.name + "<br/>";
      }
      $(document).ready(function () {
        $("#REST-button").click(function () {
					console.log("---------getData------------")
          $.ajax({
            url: "http://localhost:8080/S9AsyncDemo/helloapi",
            data: {
              format: "json",
            },
            type: "GET",
            dataType: "json",
            error: function () {
              $("#infoREST").html("<p>An error has occurred</p>");
            },
            success: function (data) {
              console.log("sucess");
							getData(data);
            },
          });
        });
      });
    </script>
  </body>
</html>
