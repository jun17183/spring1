var frmElem = document.querySelector('#frm');
var uidElem = frmElem.uid;
var upwElem = frmElem.upw;
var chkUpwElem = frmElem.chkUpw;
var unmElem = frmElem.unm;
var chkUidResultElem = frmElem.querySelector('#chkUidResult');

var btnChkIdElem = frmElem.btnChkId; //중복ID체크 버튼
btnChkIdElem.addEventListener('click', function() {
	idChkAjax(uidElem.value);
});

function idChkAjax(uid) {
	console.log(uid);
		
	fetch('/user/idChk?uid=' + uid)
	.then(function(res) {
		return res.json();
	})
	.then(function(myJson) {
		console.log(myJson);
		switch(myJson.result) {
			case 0:
			chkUidResultElem.innerText = '이 아이디는 사용할 수 있습니다.';
			break;
			case 1:
			chkUidResultElem.innerText = '이 아이디는 사용할 수 없습니다.';
			break;
		}
	});
}

function frmChk() {	
	//이상이 생기면 return false;
	
	//아이디 하나도 안 적으면 alert "아이디를 작성해 주세요" false 리턴 
	//2자 이하면 alert "아이디는 3자 이상 작성해 주세요." false 리턴
	var uidVal = uidElem.value; 
	if(uidVal.length < 3) {
		if(uidVal.length == 0) {
			alert('아이디를 작성해 주세요');
		} else {
			alert('아이디는 3자 이상 작성해 주세요.');
		}
		return false;
	}
	
	var upwVal = upwElem.value;
	var chkUpwVal = chkUpwElem.value;
	
	//비밀번호 하나도 안 적으면 alert "비밀번호를 작성해 주세요" false 리턴 
	//3자 이하면 alert "비밀번호는 4자 이상 작성해 주세요." false 리턴
	//비밀번호와 확인비밀번호가 다르면 "비밀번호를 확인해 주세요" false 리턴
	if(upwVal.length < 3) {
		if(upwVal.length == 0) {
			alert('비밀번호를 작성해 주세요');
		} else {
			alert('비밀번호는 4자 이상 작성해 주세요.');
		}
		return false;
	} else if(upwVal !== chkUpwVal) {
		alert('비밀번호를 확인해 주세요');
		return false;
	}
	
	if(unmElem.value.length < 2) {
		alert('이름은 2자 이상 작성해 주세요.');
		return false;
	}
}



