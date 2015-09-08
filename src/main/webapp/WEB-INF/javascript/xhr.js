var xhr = function() {
    function sendRequest(params) {
        var method = params.method || "GET",
            url = params.url || null,
            callback = params.callback || null,
            errback = params.errback || null,
            context = params.context || null,
            data = params.data || null;

        context = context || window;
        var req = createXMLHTTPObject();
        if (!req) return;
        if(method === "GET" && data) {
            var append = "";
            var requireAmp = false;
            for(var key in data) {
                if(data.hasOwnProperty(key)) {
                    if(requireAmp) {
                        append = append + "&"
                    } else {
                        requireAmp = true;
                    }
                    append = append + encodeURIComponent(key) + "=" + encodeURIComponent(data[key]);
                }
            }
            if(append) {
                url = url + (url.indexOf("?") !== -1 ? "&" : "?") + append;
            }
        }
        req.open(method,url,true);
        if (method !== "GET") {
            req.setRequestHeader('Content-type', 'application/json');
        }
        req.onreadystatechange = function () {
            if (req.readyState != 4) return;
            if (req.status != 200) {
                typeof errback === "function" && errback.call(context, req);
            } else {
                typeof callback === "function" && callback.call(context, JSON.parse(req.response));
            }
        };
        if (req.readyState == 4) return;
        if(method !== "GET") {
            req.send(JSON.stringify(data));
        } else {
            req.send();
        }
    }

    var XMLHttpFactories = [
        function () {return new XMLHttpRequest()},
        function () {return new ActiveXObject("Msxml2.XMLHTTP")},
        function () {return new ActiveXObject("Msxml3.XMLHTTP")},
        function () {return new ActiveXObject("Microsoft.XMLHTTP")}
    ];

    function createXMLHTTPObject() {
        var xmlhttp = false;
        for (var i=0;i<XMLHttpFactories.length;i++) {
            try {
                xmlhttp = XMLHttpFactories[i]();
            }
            catch (e) {
                continue;
            }
            break;
        }
        return xmlhttp;
    }

    return sendRequest;
};

window.xhr = xhr();