$(function(){
    {
        var weather = "夜晚";
        var card_first = $("#card").children("div:first-child");
//        var card_last = $("#card").find("div:last-child");

        switch(weather)
        {
        case "下雨":
            {
                card_first.removeClass(card_first.attr("class"));
                card_first.children("div").removeClass(card_first.children("div").attr("class"));
                card_first.addClass("card-color color-rain");
                card_first.children("div").addClass("rain");
            }
          break;
        case "雷雨":
            {
                card_first.removeClass(card_first.attr("class"));
                card_first.children("div").removeClass(card_first.children("div").attr("class"));
                card_first.addClass("card-color color-storm");
                card_first.children("div").addClass("storm");
            }
          break;
        case "晴天":
            {
                card_first.removeClass(card_first.attr("class"));
                card_first.children("div").removeClass(card_first.children("div").attr("class"));
                card_first.addClass("card-color color-sunny");
                card_first.children("div").addClass("sunny");
            }
          break;
        case "夜晚":
            {
                card_first.removeClass(card_first.attr("class"));
                card_first.children("div").removeClass(card_first.children("div").attr("class"));
                card_first.addClass("card-color color-moon");
                card_first.children("div").addClass("moon");
            }
          break;
        case "下雪":
            {
                card_first.removeClass(card_first.attr("class"));
                card_first.children("div").removeClass(card_first.children("div").attr("class"));
                card_first.addClass("card-color color-snow");
                card_first.children("div").addClass("snow");
            }
          break;
        default:
            {
                card_first.removeClass(card_first.attr("class"));
                card_first.children("div").removeClass(card_first.children("div").attr("class"));
                card_first.addClass("card-color color-sunny");
                card_first.children("div").addClass("sunny");
            }
        }
    }
});

