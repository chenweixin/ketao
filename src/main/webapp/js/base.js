$(document).ready(function() { 
	$(".addcourse-type").find("option").eq(0).text("人文科学");
	$(".addcourse-type").find("option").eq(1).text("人文科学核心");
	$(".addcourse-type").find("option").eq(2).text("社会科学");
	$(".addcourse-type").find("option").eq(3).text("社会科学核心");
	$(".addcourse-type").find("option").eq(4).text("科学技术");
	$(".addcourse-type").find("option").eq(5).text("科学技术核心");
});

/**
   * 获取学生信息json
   */
  function getStudentData(){
  	var id = $("input[name='addstudent-id']").val();
  	var pwd = $("input[name='addstudent-pwd']").val();
  	if(id == "" || pwd == "") {
  		$(".empty-error").show().css("display", "inline-block");
  		return;
  	}
  	$(".empty-error").hide();
  	var name = $("input[name='addstudent-name']").val();
  	var sex = $("input[name='addstudent_sex']:checked").val();
  	var college = $("input[name='addstudent-college']").val();
  	var major = $("input[name='addstudent-major']").val();
  	var period = $("input[name='addstudent-period']").val();
  	var avatar_url = $("input[name='addstudent-avatar_url']").val();
  	var str_data = {id: id, password: pwd, name: name, sex: sex, college: college, major: major, period: period, avatar_url: avatar_url};
  	return str_data;
  }
  function getTeacherData(){
  	var id = $("input[name='addteacher-id']").val();
  	var pwd = $("input[name='addteacher-pwd']").val();
  	if(id == "" || pwd == "") {
  		$(".empty-error").show().css("display", "inline-block");
  		return;
  	}
  	$(".empty-error").hide();
  	var name = $("input[name='addteacher-name']").val();
  	var sex = $("input[name='addteacher_sex']:checked").val();
  	var college = $("input[name='addteacher-college']").val();
  	var avatar_url = $("input[name='addteacher-avatar_url']").val();
  	var str_data = {id: id, password: pwd, name: name, sex: sex, college: college, avatar_url: avatar_url};
  	return str_data;
  }
  function getInformData(){
  	var id = $("input[name='addinform-title']").data("id");
  	var title = $("input[name='addinform-title']").val();
  	var content = $("input[name='addinform-content']").val();
  	var create_time = getNowFormatDate();
  	var str_data = {id: id, title: title, content: content, create_time: create_time};
  	return str_data;
  }
  function getCourseData(){
  	var id = $("input[name='addcourse-id']").val();
  	if(id == ""){
  		$(".empty-error").show().css("display", "inline-block");
  		return;
  	}
  	var name = $("input[name='addcourse-name']").val();
  	var location = $("input[name='addcourse-location']").val();
  	var credit = $("input[name='addcourse-credit']").val();
  	var type = $(".addcourse-type").val();
  	var teacher_id = $("input[name='addcourse-teacher_id']").val();
  	var introduction = $("input[name='addcourse-introduction']").val();
  	var create_time = getNowFormatDate();
  	var str_data = {id: id, name: name, teacher_id: teacher_id, location: location, credit: credit, type: type,introduction: introduction, create_time: create_time};
  	return str_data;
  }
  function getNoticeData(){
  	var id = $("input[name='addnotice-title']").data("id");
  	var title = $("input[name='addnotice-title']").val();
  	var content = $("input[name='addnotice-content']").val();
  	var course_id = $("input[name='addnotice-course_id']").val();
  	var create_time = getNowFormatDate();
  	var str_data = {id: id, title: title, content: content, course_id: course_id, create_time: create_time};
  	return str_data;
  }
  function getSpreadData(){
  	var id = $("input[name='addspread-title']").data("id");
  	var title = $("input[name='addspread-title']").val();
  	var sponsor = $("input[name='addspread-sponsor']").val();
  	var event_date = $("input[name='addspread-event_date']").val();
  	var event_address = $("input[name='addspread-event_address']").val();
  	var event_describe = $("input[name='addspread-event_describe']").val();
  	var poster_url = $("input[name='addspread-poster_url']").val();
  	var create_time = getNowFormatDate();
  	var str_data = {id: id, title: title, sponsor: sponsor, event_date: event_date, event_address: event_address, event_describe: event_describe, poster_url: poster_url, create_time: create_time};
  	return str_data;
  }
function getdate(){
	var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes();
    return currentdate;
}
function getNowFormatDate() {
    var date = new Date();
    
    return date.getTime();
}
/**
 * 信息页面  编辑功能
 */
function edit(){
	$(".info-state").hide();
	$(".edit-state").show().css("display", "inline-block");
	$(".edit-btn").show();
}
function cancleEdit(){
	$(".info-state").show().css("display", "inline-block");
	$(".edit-state").hide();
	$(".edit-btn").hide();
	$(".text-error").hide();
}
/**
 * 列表结果页面 复选框功能
 */
$(".item-ids").on('click', function(){
	if(checkIsAll()){
		$("input[name='checkbox-selectAll']")[0].checked = true;
	}
	else{
		$("input[name='checkbox-selectAll']")[0].checked = false;
	}
});
function isSelectAll(div){
	if(!div.checked){
		unselectAll();
	}
	else{
		selectAll();
	} 
}
function selectAll(){
	var items = $(".item-ids");
	for(var i = 0; i < items.length; i++){
		items[i].checked = true;
	}
}
function unselectAll(){
	var items = $(".item-ids");
	for(var i = 0; i < items.length; i++){
		items[i].checked = false;
	}
}
function checkIsAll(){
	var items = $(".item-ids");
	for(var i = 0; i < items.length; i++){
		if(!items[i].checked) return false;
	}
	return true;
}