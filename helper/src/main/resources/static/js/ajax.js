/**
 * 
 */
 
 function ajaxRequest(data, callback, requestUrl, requestMethod) {
	var httpRequest = new XMLHttpRequest();

	if (!httpRequest) {//실패
		return false;
	}
	//onreadystatechange
	httpRequest.onreadystatechange = function() {
		if (httpRequest.readyState === XMLHttpRequest.DONE) {
			if (httpRequest.status === 200) {
				callback(httpRequest.responseText);
			} else {
				alert('request에 뭔가 문제가 있어요.');
			}
		}
	};
	httpRequest.open(requestMethod, requestUrl);
	//httpRequest.setRequestHeader('Content-Type', 'application/json');
	httpRequest.send(data);
}

function alerter(response){
	if(response != ''){
		//alert(response);	
	}
	location.reload();
}