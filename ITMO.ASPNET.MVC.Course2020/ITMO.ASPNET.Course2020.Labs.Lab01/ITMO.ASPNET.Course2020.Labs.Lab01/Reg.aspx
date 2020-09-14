<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Reg.aspx.cs" Inherits="ITMO.ASPNET.Course2020.Labs.Lab01.Start" %>

<!DOCTYPE html>

<head runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <link rel="stylesheet" href="Styles.css" />
</head>

<div> 
<form id="form1" runat="server">

    <div class="rek"> 
        <div> <h1>Форма регистрации</h1> <p></p> </div>
    </div>

    <div>
        <label>Ваше имя:</label>
        <asp:TextBox ID="name" runat="server"></asp:TextBox> 
    </div>
    <div>
        <label>Ваш email:</label>
        <asp:TextBox ID="email" runat="server"></asp:TextBox>
    </div>
    <div>
        <label>Ваш телефон:</label>
        <asp:TextBox ID="phone" runat="server"></asp:TextBox> 
    </div>
    <div> 
        <label>Вы будете делать доклад?</label> 
        <asp:CheckBox ID="CheckBoxYN" runat="server" />
    </div>
    <div>
        <button type="submit">Отправить ответ на приглашение RSVP</button>
    </div>

    

    </form>
</div>


