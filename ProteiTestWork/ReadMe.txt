bubyrevdmitriy@gmail.com

____Запрос на получение общей информации о сервисе_____
fetch(
  '/', 
  { 
    method: 'GET', 
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify()
  }
).then(result => result.json().then(console.log))

____Запрос на получение общей информации о странице регистрации_____
fetch(
  '/registration', 
  { 
    method: 'GET', 
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify()
  }
).then(result => result.json().then(console.log))

____Запрос на регистрацию_____
fetch(
  '/registration', 
  { 
    method: 'POST', 
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ username: 'user', password: '123', email: 'user@gmail.com', phoneNumber: '+123456789'})
  }
).then(result => result.json().then(console.log))

____Запрос на получение общей информации о странице логина_____
fetch(
  '/login', 
  { 
    method: 'GET', 
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify()
  }
).then(result => result.json().then(console.log))

____Запрос на вход в систему_____
fetch(
  '/login', 
  { 
    method: 'POST', 
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ username: 'admin', password: '123' })
  }
).then(result => result.json().then(console.log))

____Запрос на получение общей информации о списке пользователей_____
fetch(
  '/user', 
  { 
    method: 'GET', 
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify()
  }
).then(result => result.json().then(console.log))

____Запрос на изменение статуса пользователя_____
fetch(
  '/user', 
  { 
    method: 'PATCH', 
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ id: '1', newStatus: 'ONLINE' })
  }
).then(result => result.json().then(console.log))


____Запрос на получение информации конкретном пользователе_____
fetch(
  '/user/1', 
  { 
    method: 'GET', 
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify()
  }
).then(result => result.json().then(console.log))
﻿
