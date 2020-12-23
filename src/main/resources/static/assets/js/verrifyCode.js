/* 
Author:LiuCenyu !!!!!!!!!!!!!!!!!!!!!!
date: 12.11 !!!!!!!!!!!!!!!!!!
*/

$(function(){
    var show_num = [];
    draw(show_num);

    $("#canvas").on('click',function(){
     draw(show_num);
     var code_icon = $("#code-icon");
     code_icon.removeClass();
     code_icon.addClass('glyphicon glyphicon-remove');
     code_icon.css({"color":"red","display":"block"});
     $("#verifyCode").keyup();
    })

    $("#verifyCode").on('blur keyup',function(){
     var code_icon = $("#code-icon");
     var val = $("#verifyCode").val().toLowerCase();
     var num = show_num.join("");   //用于把数组中的所有元素放入一个字符串。
     if(val == num){
        code_icon.removeClass();
        code_icon.addClass('glyphicon glyphicon-ok');
        code_icon.css({"color":"green","display":"block"});
     }else{
        code_icon.removeClass();
        code_icon.addClass('glyphicon glyphicon-remove');
        code_icon.css({"color":"red","display":"block"});
     }
    })

   })
   //生成并渲染出验证码图形
   function draw(show_num) {
        var canvas_width=$('#canvas').width();
        var canvas_height=$('#canvas').height();
        var canvas = document.getElementById("canvas");//获取到canvas的对象
        var context = canvas.getContext("2d");//获取到canvas画图的环境
        canvas.width = canvas_width;
        canvas.height = canvas_height;
        var sCode = "a,b,c,d,e,f,g,h,i,j,k,m,n,p,q,r,s,t,u,v,w,x,y,z,A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
        var aCode = sCode.split(",");  //把字符串分割成字符串数组。
        var aLength = aCode.length;//获取到数组的长度
        for (var i = 0; i < 4; i++) { //这里的for循环可以控制验证码位数（如果想显示6位数，4改成6即可）
            var j = Math.floor(Math.random() * aLength);//获取到随机的索引值,random() 方法可返回介于 0（包含） ~ 1（不包含） 之间的一个随机数。
            // var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
            var deg = Math.random() - 0.5; //产生一个随机弧度
            var txt = aCode[j];//得到随机的一个内容
            show_num[i] = txt.toLowerCase();
            var x = 10 + i * 20;//文字在canvas上的x坐标
            var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
            context.font = "bold 35px 微软雅黑";
            context.translate(x, y);
            context.rotate(deg);
            context.fillStyle = randomColor();
            console.dir(context.font.width);
            context.fillText(txt, canvas_width/8 , canvas_height/3);
            context.rotate(-deg);
            context.translate(-x, -y);
        }
        for (var i = 0; i <= 5; i++) { //验证码上显示线条
            context.strokeStyle = randomColor();
            context.beginPath();
            context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.stroke();
        }
        for (var i = 0; i <= 30; i++) { //验证码上显示小点
            context.strokeStyle = randomColor();
            context.beginPath();
            var x = Math.random() * canvas_width;
            var y = Math.random() * canvas_height;
            context.moveTo(x, y);
            context.lineTo(x + 1, y + 1);
            context.stroke();
        }
   }
   //得到随机的颜色值
    function randomColor() {
    var r = Math.floor(Math.random() * 256);
    var g = Math.floor(Math.random() * 256);
    var b = Math.floor(Math.random() * 256);
    return "rgb(" + r + "," + g + "," + b + ")";
    }