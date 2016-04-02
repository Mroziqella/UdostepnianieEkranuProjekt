<%--
  Created by IntelliJ IDEA.
  User: Kamil
  Date: 29/03/2016
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        var binary = '';
        var nowyImage = new Image()
        function arrayBufferToBase64( buffer ) {

            var bytes = new Uint8Array( buffer );
            var len = bytes.byteLength;
            for (var i = 0; i < len; i++) {
                binary += String.fromCharCode( bytes[ i ] );
            }
            return binary;
        }


        function wyswietl(){
            //arrayBufferToBase64(array);
            var image;
            var req = new XMLHttpRequest();
            req.open('GET', '/image/user', true); /* Argument trzeci, wartość true, określa, że żądanie ma być asynchroniczne */
            req.onreadystatechange = function (aEvt) {
                nowyImage.src="data:image/jpg;base64,"+req.responseText;
                document.getElementById("ItemPreview").src = nowyImage.src;

            };
            req.send(null);
            /*
            document.getElementById("ItemPreview").src = "data:image/jpg;base64,"+http.get('/image/user');
            document.getElementById("diw").innerHTML = http.get('/image/user');*/

        ;


            binary='';
            setTimeout(wyswietl, 500);
        }
    </script>
</head>
<body onload="wyswietl()">
<img id="ItemPreview" src="" height="50%" />
<div id="diw"></div>
</body>
</html>
