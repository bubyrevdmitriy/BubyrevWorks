<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<#import "parts/userInf.ftlh" as u>

<@c.pageWeb>

    <@u.userInf false/>


    <#include "parts/orderListUserEdit.ftlh">

    <#if isAdmin>
    <form  method="post" enctype="multipart/form-data" class="form-group m-4">
        <h5>Изменение пользователя</h5>

        <label class="col-sm-2 col-form-label"> Роли в системе :  </label>
        <br>
        <div class="form-group row">
            <#list roles as role>
                <div class="col-sm-7 col-md-7 col-lg-7 col-xl-7 ml-15">
                    <label>
                        <input type="checkbox" name="${role}" ${userCurrent.roles?seq_contains(role)?string("checked","")}/>

                        ${role?contains("USER")?string("Читатель","")}
                        ${role?contains("LIBRARIAN")?string("Библиотекарь","")}
                        ${role?contains("ADMIN")?string("Администратор","")}
                    </label>
                </div>
            </#list>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Имя в системе :  </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="Имя" value="${userCurrent.username!''}"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Имя :  </label>
            <div class="col-sm-6">
                <input type="text" name="firstName" class="form-control" placeholder="Имя" value="${userCurrent.firstName!''}"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Фамилия :  </label>
            <div class="col-sm-6">
                <input type="text" name="lastName" class="form-control" placeholder="Фамилия" value="${userCurrent.lastName!''}"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Отчество :  </label>
            <div class="col-sm-6">
                <input type="text" name="middleName" class="form-control" placeholder="Отчество" value="${userCurrent.middleName!''}"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label" > Пароль: </label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Пароль"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label" > Электронная почта: </label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" placeholder="some@some.com" value="${userCurrent.email!''}"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label" > Телефон: </label>
            <div class="col-sm-6">
                <input type="phone" name="phone" class="form-control" placeholder="+00000000000" value="${userCurrent.phone!''}"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label" > Фото пользователя: </label>
            <div class="custom-file col-sm-6">
                <input type="file" class="form-control" name="file">
            </div>
        </div>


        <input type="hidden" name="userId" value="${userCurrent.id}" />
        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <button class="btn btn-primary" type="submit">Сохранить</button>

        <a class="btn btn-danger m-4" href="/user/delete/${userCurrent.id}">Удалить пользователя</a>

    </form>
</#if>



</@c.pageWeb>