 <h2 style="font-style:italic;">FinderTweets - this is a program for finding tweets on the Twitter </h2>
<h4>Using:</h4>
<ul>
	
<li>Spring Boot</li> 
<li>Mongo DB</li> 
<li>Docker</li> 
</ul>

<p><samp><u>Endpoints</u></samp>:</p>

<ul>

<li>GET /tweets/all - returns all tweets</li>
<li>POST /tweets/find - search tweets</li>
<li>DELETE /tweets/del - delete all tweets from database</li>
</ul>

 <p><u>For example:</u></p>
 <p>localhost:8282/tweets/find?search=java&amp;counts=10&amp;lan=ru&amp;maxPage=10</p>

<h4>Dockerize application:</h4>
<p><samp><u>Example</u></samp>:</p>

<li>1. git clone hhttps://github.com/WadoZzz/FinderTweets.git -b finderTweets</li>	
<li>2. cd FinderTweets && docker build -t wadoz/finder .</li>	
<li>3. docker-compose up</li>
<li>4. docker run -it --link mongo-connect:mongo --net findertweets_default wadoz/finder:latest</li>

