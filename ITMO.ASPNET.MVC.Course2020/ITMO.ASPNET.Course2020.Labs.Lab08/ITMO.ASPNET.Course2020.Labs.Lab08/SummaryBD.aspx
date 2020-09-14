<%@ Page Title="" Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="true" CodeBehind="SummaryBD.aspx.cs" Inherits="ITMO.ASPNET.Course2020.Labs.Lab08.SummaryBD" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <h2>Список участников<asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" DataSourceID="LinqDataSource1">
        <Columns>
            <asp:BoundField DataField="Name" HeaderText="Имя" ReadOnly="True" SortExpression="Name" />
            <asp:BoundField DataField="Email" HeaderText="Электронный адрес" ReadOnly="True" SortExpression="Email" />
            <asp:BoundField DataField="Phone" HeaderText="Телефон" ReadOnly="True" SortExpression="Phone" />
            <asp:BoundField DataField="Rdata" DataFormatString="{0:d}" HeaderText="Дата регистрации" ReadOnly="True" SortExpression="Rdata" />
        </Columns>
        </asp:GridView>
        <asp:LinqDataSource ID="LinqDataSource1" runat="server" ContextTypeName="ITMO.ASPNET.Course2020.Labs.Lab04.SampleContext" EntityTypeName="" Select="new (Name, Email, Phone, Rdata)" TableName="GuestResponses">
        </asp:LinqDataSource>
    </h2>



</asp:Content>
