package org.sisioh.dddbase.event

import scala.language.higherKinds

/**
 * [[org.sisioh.dddbase.event.DomainEventPublisher]]のための骨格実装。
 *
 * @tparam A ドメインイベントの型
 * @tparam M モナドの型
 * @tparam R モナドの要素型
 */
trait DomainEventPublisherSupport
[A <: DomainEvent[_], M[+B], R]
  extends DomainEventPublisher[A, M, R] {

  /**
   * [[org.sisioh.dddbase.event.DomainEventSubscriber]]の集合
   */
  protected val subscribers: Seq[DomainEventSubscriber[A, M, R]]

  /**
   * 新しいインスタンスを作成する。
   *
   * @param subscribers [[org.sisioh.dddbase.event.DomainEventSubscriber]]の列。
   * @return 新しいインスタンス
   */
  protected def createInstance(subscribers: Seq[DomainEventSubscriber[A, M, R]]): This

  def publish(event: A): Seq[M[R]] = {
    subscribers.map(_.handleEvent(event))
  }

  def subscribe(subscriber: DomainEventSubscriber[A, M, R]): This = {
    createInstance(subscribers :+ subscriber)
  }

  def unsubscribe(subscriber: DomainEventSubscriber[A, M, R]): This = {
    createInstance(subscribers.filterNot(_ == subscriber))
  }

}