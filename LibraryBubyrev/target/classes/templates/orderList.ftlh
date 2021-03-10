<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#include "parts/security.ftlh">
<#import "parts/pager.ftlh" as p>

<@c.pageWeb>

    <div class="form-row m-3">
        <form method = "get" action="/orderList" class="form-inline">
            <input type="text" class="form-control m-2" name="orderId" value="${orderId!''}" placeholder="Введите id заказа"/>
            <button type="submit" class="btn btn-primary ml-4">Найти по id заказа</button>
        </form>
    </div>



    <#include "parts/orderListFull.ftlh">


</@c.pageWeb>