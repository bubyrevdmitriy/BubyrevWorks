<#include "security.ftlh">

<form  method="post" enctype="multipart/form-data" class="form-group m-4">

    <h5>Изменение пользователя</h5>
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

    <input type="hidden" name="userId" value="${user.id}" />
    <input type="hidden" name="_csrf" value="${_csrf.token}" />

    <button class="btn btn-primary" type="submit">Сохранить</button>
</form>