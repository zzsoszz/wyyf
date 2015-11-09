/**
 * Created by agan on 2015/7/16.
 */
require(['jquery','superSlide','bootstrap'],function($){

    //input 样式改变
    if($('div.tab-box').length > 0){
        $('div.tab-box').find('input[type="text"]').on('focusin focusout',function(event){
            event = event || window.event;
            var old = $(this)[0].defaultValue;
            if(event.type == 'focusin'){
                $(this).css({borderColor:'#f25618'});
                if($(this).val() == old){
                    $(this).val(' ');
                }
            }else{
                $(this).removeAttr('style');
                if($(this).val() == ' '){
                    $(this).val(old);
                }
            }
        });
    }

    /*滚动效果*/
    $(".promot-box .slide").slide({ mainCell:"ul",vis:4,prevCell:".sPrev",nextCell:".sNext",effect:"leftLoop"});

    /*免费验房tab切换*/
    $(".user-left-main").slide({
        titCell:".tab-title a",
        mainCell:".tab-box",
        trigger:"click"
    });
    /*抢相因tab*/
    $('.promot-box').slide({
        titCell:".time-line ul li span",
        mainCell:".slide",
        trigger:"click"
    });
    /*找师傅*/
    $('.find-col').slide({
        titCell:".module-title .tab span",
        mainCell:".find-box",
        trigger:"click"
    });
    //导航
    $(".mycontainer .mynav li a").focus(function(){
        console.log("2222222222222")
        $(this).parents("li").find("a").removeClass("cur");

        $(this).siblings("ul").show();
    })
    $(".mynav li a").each(function(){
        if($($(this))[0].href==String(window.location)){
            //$(".mynav li.cur").removeClass("cur");
            $(this).parents("li").addClass("cur");
        }
    });
    $(".slideGroup .slideBox").slide({
        mainCell:"ul",
        vis:4,
        prevCell:".sPrev",
        nextCell:".sNext",
        effect:"leftLoop"
    })


});
