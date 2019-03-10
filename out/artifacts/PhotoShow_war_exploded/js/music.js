$(document).ready(function(){
  var playlist = [{
      title:" 泰坦尼克号",
      artist:"Celine Dion",
      mp3:"mp3/1.mp3",
      poster: "images/1.jpg"
    },{
      title:"There You Will Be",
      artist:"Faith Hil",
      mp3:"mp3/2.mp3",
      poster: "images/2.jpg"
    },{
      title:"AOA - Moya",
	  artist:"AOA",
      mp3: "mp3/3.mp3",
      poster: "images/3.jpg"
  },{
    title:"Tell Me",
    artist:"俞永镇",
    mp3:"mp3/4.mp3",
    poster: "images/2.jpg"
  },{
    title:"Lia-鸟之诗",
    artist:"HilLia",
    mp3:"mp3/5.mp3",
    poster: "images/2.jpg"
  },{
    title:"See You Again",
    artist:"速度与激情",
    mp3:"mp3/6.mp3",
    poster: "images/2.jpg"
  },{
    title:"我的天空",
    artist:"贝贝",
    mp3:"mp3/7.mp3",
    poster: "images/2.jpg"
  },{
    title:"筷子兄弟-老男孩",
    artist:"老男孩",
    mp3:"mp3/8.mp3",
    poster: "images/2.jpg"
  }, {
    title: "那英-雾里看花",
    artist: "那英",
    mp3: "mp3/9.mp3",
    poster: "images/2.jpg"
  }];
  
  var cssSelector = {
    jPlayer: "#jquery_jplayer",
    cssSelectorAncestor: ".music-player"
  };
  
  var options = {
    swfPath: "Jplayer.swf",
    supplied: "ogv, m4v, oga, mp3"
  };
  
  var myPlaylist = new jPlayerPlaylist(cssSelector, playlist, options);
  
});