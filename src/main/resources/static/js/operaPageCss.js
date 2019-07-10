(function () {

    $(function(){

        //获取所有的子checkbox
        var boxes = $("input[id^='box_']");

        //为全选绑定点击事件
        $("#checkAll").click(function(){
            //让所有的子checkbox的状态和 全选的状态一致
            boxes.prop("checked",this.checked);

            //触发数据行的  mouseover或者mouseout事件
            $("tr[id^='dataTr_']").trigger(this.checked? "mouseover" : "mouseout");

        })

        //为所有的子checkbox绑定点击事件
        boxes.click(function(){
            //获取选中的checkbox的个数
            //var len = boxes.filter(":checked").length;
            var len = $("input[id^='box_']:checked").length;
            //如果所有的子checkbox是选中的，那么全选应该选中
            $("#checkAll").prop("checked",boxes.length == len);
        })


        //给数据行绑定  mouseover以及mouseout事件
        $("tr[id^='dataTr_']").mouseover(function(){
            $(this).css("background","#eeccff");
        }).mouseout(function(){
            //如果当前行中的  checkbox是选中的则鼠标离开的时候不去掉背景色
            //获取tr的id
            var trId = this.id;
            //获取当前中的checkbox的id
            var boxId = trId.replace("dataTr","box");
            //获取checkbox的选中状态
            var flag = $("#"+boxId).prop("checked");
            if(!flag){
                $(this).css("background","");
            }
        })
    })

})()