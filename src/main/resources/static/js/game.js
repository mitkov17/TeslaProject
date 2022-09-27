class Road
{
	constructor(image, y)
	{
		this.x = 0;
		this.y = y;
		this.loaded = false;

		this.image = new Image();
		
		var obj = this;

		this.image.addEventListener("load", function () { obj.loaded = true; });

		this.image.src = image;
	}

	Update(road) 
	{
		this.y += speed; //Движение изображения вниз с каждым кадром

		if(this.y > window.innerHeight) //Если изображение покинуло экран, оно изменит свое положение
		{
			this.y = road.y - canvas.width + speed; //Новая позиция зависит от второго объекта (Дорога)
		}
	}
}

class Car
{
	constructor(image, x, y, isPlayer)
	{
		this.x = x;
		this.y = y;
		this.loaded = false;
		this.dead = false;
		this.isPlayer = isPlayer;

		this.image = new Image();

		var obj = this;

		this.image.addEventListener("load", function () { obj.loaded = true; });

		this.image.src = image;
	}

	Update()
	{
		if(!this.isPlayer)
		{
			this.y += speed;
		}

		if(this.y > canvas.height + 50)
		{
			this.dead = true;
		}
	}

	Collide(car)
	{
		var hit = false;

		if(this.y < car.y + car.image.height * scale && this.y + this.image.height * scale > car.y) //Если есть столкновение по y
		{
			if(this.x + this.image.width * scale > car.x && this.x < car.x + car.image.width * scale) //Если есть столкновение по x
			{
				hit = true;
			}
		}

		return hit;
	}

	Move(v, d) 
	{
		if(v == "x") //Двигаясь по x
		{
			d *= 2;

			this.x += d; //Смена позиции

			//Откат изменений, если машина ушла с экрана
			if(this.x + this.image.width * scale > canvas.width)
			{
				this.x -= d; 
			}
	
			if(this.x < 0)
			{
				this.x = 0;
			}
		}
		else //Двигаясь по y
		{
			this.y += d;

			if(this.y + this.image.height * scale > canvas.height)
			{
				this.y -= d;
			}

			if(this.y < 0)
			{
				this.y = 0;
			}
		}
		
	}
}


const UPDATE_TIME = 1000 / 60;

var timer = null;

var canvas = document.getElementById("canvas"); //Получение холста из DOM
var ctx = canvas.getContext("2d"); //Получение контекста — через него можно работать с холстом

var scale = 0.13;

Resize(); //При загрузке страницы задаётся размер холста

window.addEventListener("resize", Resize); //При изменении размеров окна будут меняться размеры холста

//Запрет открывать контекстное меню для улучшения игры на мобильных устройствах
canvas.addEventListener("contextmenu", function (e) { e.preventDefault(); return false; }); 

window.addEventListener("keydown", function (e) { KeyDown(e); }); //Прослушивание событий клавиатуры

var objects = []; //Массив игровых объектов

var roads = 
[
	new Road("game_img/road.jpg", 0),
	new Road("game_img/road.jpg", canvas.width)
]; //Массив с фонами

var player = new Car("game_img/tesla_cyber.png", canvas.width / 2, canvas.height / 2, true); //Объект игрока


var speed = 8;

Stop();


function Start()
{
	if(!player.dead)
	{
		timer = setInterval(Update, UPDATE_TIME); //Обновление игры 60 раз в секунду
	}
}

function Stop()
{
	clearInterval(timer); //Остановка игры
	timer = null;
}

function Update() 
{
	roads[0].Update(roads[1]);
	roads[1].Update(roads[0]);

	if(RandomInteger(0, 10000) > 9700) //Создание новой машины
	{
		objects.push(new Car("game_img/model3.png", RandomInteger(30, canvas.width - 50), RandomInteger(250, 400) * -1, false));
	}

	player.Update();

	if(player.dead)
	{
		alert_msg("Well.. Maybe it was a little too hard. But it didn't go through!");
		//alert_msg("Test drive is over!");
		Stop();
	}

	var isDead = false; 

	for(var i = 0; i < objects.length; i++)
	{
		objects[i].Update();

		if(objects[i].dead)
		{
			isDead = true;
		}
	}

	if(isDead)
	{
		objects.shift();
	}

	var hit = false;

	for(var i = 0; i < objects.length; i++)
	{
		hit = player.Collide(objects[i]);

		if(hit)
		{
			alert_msg("Well.. Maybe it was a little too hard. But it didn't go through!");
			//alert_msg("Test drive is over!");
			Stop();
			player.dead = true;
			break;
		}
    }

    Draw();
}

function Draw() //Работа с графикой
{
	ctx.clearRect(0, 0, canvas.width, canvas.height); //Очистка холста

	for(var i = 0; i < roads.length; i++)
	{
		ctx.drawImage
		(
			roads[i].image, //Картинка
			0, //Первый X на изображении
			0, //Первый Y на изображении
			roads[i].image.width, //Последний X на изображении
			roads[i].image.height, //Последний Y на изображении
			roads[i].x, //X на холсте
			roads[i].y, //Y на холсте
			canvas.width, //Ширина холста
			canvas.width //Высота холста
		);
	}

	DrawCar(player);

	for(var i = 0; i < objects.length; i++)
	{
		DrawCar(objects[i]);
    }
}

function DrawCar(car)
{
	ctx.drawImage
	(
		car.image, 
		0, 
		0, 
		car.image.width, 
		car.image.height, 
		car.x, 
		car.y, 
		car.image.width * scale, 
		car.image.height * scale 
	);
}

function KeyDown(e)
{
	switch(e.keyCode)
	{
		case 65: //Aa
			player.Move("x", -speed);
			break;

		case 68: //Dd
			player.Move("x", speed);
			break;

		case 87: //Ww
			player.Move("y", -speed);
			break;

		case 83: //Ss
			player.Move("y", speed);
			break;

		case 106: //*
			if(timer == null)
			{
				Start();
			}
			else
			{
				Stop();
			}
			break;
	}
}

function Resize()
{
	canvas.width = window.innerWidth;
	canvas.height = window.innerHeight;
}

function RandomInteger(min, max) 
{
	let rand = min - 0.5 + Math.random() * (max - min + 1);
	return Math.round(rand);
}