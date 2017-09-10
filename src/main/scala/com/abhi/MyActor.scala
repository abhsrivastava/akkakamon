package com.abhi

/**
  * Created by ASrivastava on 9/9/17.
  */

import akka.actor.{Actor, ActorLogging, Props}
import kamon.Kamon

class MyActor extends Actor with ActorLogging {

   override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
      log.error(s"The actor is going to restart because of ${reason.getMessage}")
      super.preRestart(reason, message)
   }

   override def postRestart(reason: Throwable): Unit = {
      log.error("the actor restart complete")
      super.postRestart(reason)
   }

   override def postStop(): Unit = {
      log.error("the actor has stopped")
      super.postStop()
   }

   override def receive: Receive = {
      case _ =>
         val kamonContext = Kamon.tracer.newContext("test-trace")
         log.debug("came inside actor")
         Thread.sleep(2000)
         println("done " + System.currentTimeMillis.toString)
         kamonContext.finish

   }
}

object MyActor {
   def props() = Props(classOf[MyActor])
}
