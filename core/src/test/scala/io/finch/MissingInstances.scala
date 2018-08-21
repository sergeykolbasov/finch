package io.finch

import cats.Eq
import cats.effect.{Effect, IO}
import com.twitter.io.Buf
import com.twitter.util.{Return, Throw, Try}
import io.catbird.util.twitterTryInstance

/**
 * Type class instances for non-Finch types.
 */
trait MissingInstances {
  implicit def eqTry[A](implicit A: Eq[A]): Eq[Try[A]] = Eq.instance {
    case (Return(a), Return(b)) => A.eqv(a, b)
    case (Throw(x), Throw(y)) => x == y
    case _ => false
  }

  implicit def eqBuf: Eq[Buf] = Eq.fromUniversalEquals

}
