<%@ page import="java.util.* ,dal.asdc.ludo_board_structure.Ludo_board_formation"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="ludo_board.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ludo Game</title>
</head>
<body>
<%	
	Map<String,String> token_positions_map = new HashMap<String,String>();
	Ludo_board_formation tkn_pos=new Ludo_board_formation();
	token_positions_map = tkn_pos.board_attributes();
%>

<div class="game">
    <div class="house green">
        <div class="box">
            <div class="square square-one green"><%=token_positions_map.get("{11,3}")%></div>
            <div class="square square-two green"><%=token_positions_map.get("{12,3}")%></div>
            <div class="square square-three green"><%=token_positions_map.get("{12,2}")%></div>
            <div class="square square-four green"><%=token_positions_map.get("{11,2}")%></div>
        </div>
    </div>

    <div class="house yellow" style="right: 0">
        <div class="box">
            <div class="square square-one yellow"><%=token_positions_map.get("{11,12}")%></div>
            <div class="square square-two yellow"><%=token_positions_map.get("{11,11}")%></div>
            <div class="square square-three yellow"><%=token_positions_map.get("{12,11}")%></div>
            <div class="square square-four yellow"><%=token_positions_map.get("{12,12}")%></div>
        </div>
    </div>

    <div class="house red" style="bottom: 0">
        <div class="box">
            <div class="square square-one red"><%=token_positions_map.get("{2,3}")%></div>
 			<div class="square square-two red"><%=token_positions_map.get("{3,3}")%></div>
 			<div class="square square-three red"><%=token_positions_map.get("{3,2}")%></div>
 			<div class="square square-four red"><%=token_positions_map.get("{2,2}")%></div>
        </div>
    </div>

    <div class="house blue" style="bottom: 0;right: 0">
        <div class="box">
            <div class="square square-one blue"><%=token_positions_map.get("{2,12}")%></div>
            <div class="square square-two blue"><%=token_positions_map.get("{2,11}")%></div>
            <div class="square square-three blue"><%=token_positions_map.get("{3,12}")%></div>
            <div class="square square-four blue"><%=token_positions_map.get("{3,11}")%></div>
        </div>
    </div>

    <div class="home"></div>
    <div class="cells" style="top: 40%;"></div>
    <div class="cells g-start" style="top: 40%;left:6.66%;"></div>
    <div class="cells" style="top: 40%;left:13.32%;"></div>
    <div class="cells" style="top: 40%;left:19.98%;"></div>
    <div class="cells" style="top: 40%;left:26.64%;"></div>
    <div class="cells" style="top: 40%;left:33.3%;"></div>

    <div class="cells" style="top: 0;left:40%;"></div>
    <div class="cells" style="top: 6.66%;left:40%;"></div>
    <div class="cells safe" style="top: 13.32%;left:40%;"></div>
    <div class="cells" style="top: 19.98%;left:40%;"></div>
    <div class="cells" style="top: 26.64%;left:40%;"></div>
    <div class="cells" style="top: 33.3%;left:40%;"></div>

    <div class="cells" style="top: 0;left:46.66%;"></div>
    <div class="cells yellow" style="top: 6.66%;left:46.66%;"></div>
    <div class="cells yellow" style="top: 13.32%;left:46.66%;"></div>
    <div class="cells yellow" style="top: 19.98%;left:46.66%;"></div>
    <div class="cells yellow" style="top: 26.64%;left:46.66%;"></div>
    <div class="cells yellow" style="top: 33.3%;left:46.66%;"></div>

    <div class="cells" style="top: 0;left:53.32%;"></div>
    <div class="cells y-start" style="top: 6.66%;left:53.32%;"></div>
    <div class="cells" style="top: 13.32%;left:53.32%;"></div>
    <div class="cells" style="top: 19.98%;left:53.32%;"></div>
    <div class="cells" style="top: 26.64%;left:53.32%;"></div>
    <div class="cells" style="top: 33.3%;left:53.32%;"></div>

    <div class="cells" style="top: 40%; right: 33.3%"></div>
    <div class="cells" style="top: 40%;right:26.64%;"></div>
    <div class="cells" style="top: 40%;right:19.98%;"></div>
    <div class="cells safe" style="top: 40%;right:13.32%;"></div>
    <div class="cells" style="top: 40%;right:6.66%;"></div>
    <div class="cells" style="top: 40%;right:0;"></div>

    <div class="cells blue" style="top: 46.66%; right: 33.3%"></div>
    <div class="cells blue" style="top: 46.66%;right:26.64%;"></div>
    <div class="cells blue" style="top: 46.66%;right:19.98%;"></div>
    <div class="cells blue" style="top: 46.66%;right:13.32%;"></div>
    <div class="cells blue" style="top: 46.66%;right:6.66%;"></div>
    <div class="cells" style="top: 46.66%;right:0;"></div>

    <div class="cells" style="top: 53.32%; right: 33.3%"></div>
    <div class="cells" style="top: 53.32%;right:26.64%;"></div>
    <div class="cells" style="top: 53.32%;right:19.98%;"></div>
    <div class="cells" style="top: 53.32%;right:13.32%;"></div>
    <div class="cells b-start" style="top: 53.32%;right:6.66%;"></div>
    <div class="cells" style="top: 53.32%;right:0;"></div>

    <div class="cells" style="bottom: 0;left:53.32%;"></div>
    <div class="cells" style="bottom: 6.66%;left:53.32%;"></div>
    <div class="cells safe" style="bottom: 13.32%;left:53.32%;"></div>
    <div class="cells" style="bottom: 19.98%;left:53.32%;"></div>
    <div class="cells" style="bottom: 26.64%;left:53.32%;"></div>
    <div class="cells" style="bottom: 33.3%;left:53.32%;"></div>

    <div class="cells" style="bottom: 0;left:46.66%;"></div>
    <div class="cells red " style="bottom: 6.66%;left:46.66%;"></div>
    <div class="cells red" style="bottom: 13.32%;left:46.66%;"></div>
    <div class="cells red" style="bottom: 19.98%;left:46.66%;"></div>
    <div class="cells red" style="bottom: 26.64%;left:46.66%;"></div>
    <div class="cells red" style="bottom: 33.3%;left:46.66%;"></div>

    <div class="cells" style="bottom: 0;left:40%;"></div>
    <div class="cells r-start" style="bottom: 6.66%;left:40%;"></div>
    <div class="cells" style="bottom: 13.32%;left:40%;"></div>
    <div class="cells" style="bottom: 19.98%;left:40%;"></div>
    <div class="cells" style="bottom: 26.64%;left:40%;"></div>
    <div class="cells" style="bottom: 33.3%;left:40%;"></div>

    <div class="cells" style="top: 53.32%; left: 33.3%"></div>
    <div class="cells" style="top: 53.32%;left:26.64%;"></div>
    <div class="cells" style="top: 53.32%;left:19.98%;"></div>
    <div class="cells safe" style="top: 53.32%;left:13.32%;"></div>
    <div class="cells" style="top: 53.32%;left:6.66%;"></div>
    <div class="cells" style="top: 53.32%;left:0;"></div>

    <div class="cells green" style="top: 46.66%;left: 33.3%"></div>
    <div class="cells green" style="top: 46.66%;left:26.64%;"></div>
    <div class="cells green" style="top: 46.66%;left:19.98%;"></div>
    <div class="cells green" style="top: 46.66%;left:13.32%;"></div>
    <div class="cells green" style="top: 46.66%;left:6.66%;"></div>
    <div class="cells" style="top: 46.66%;left:0;"></div>


</div>
</body>
</html>