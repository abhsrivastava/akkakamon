package com.abhi

import akka.actor.ActorSystem

import scala.concurrent._
import scala.concurrent.duration._
import akka.pattern.ask
import kamon.Kamon
import akka.util.Timeout

/**
  * Created by ASrivastava on 9/9/17.
  */
object MyApp extends App {
   Kamon.start()
   val actorSystem = ActorSystem("sandbox-actor-system")
   implicit val timeout = new Timeout(5 seconds)
   val myActor = actorSystem.actorOf(MyActor.props(), "myactor")
   import actorSystem.dispatcher
   val c = actorSystem.scheduler.schedule(2 seconds, 2 milliseconds) {
      println("loading!")
      myActor ! "ping"
   }
   sys.addShutdownHook({
      Await.result(actorSystem.terminate(), Duration.Inf)
      Kamon.shutdown()
   })
   readLine()
   println("will not send more load")
   c.cancel()
   readLine()
   System.exit(0)
}
