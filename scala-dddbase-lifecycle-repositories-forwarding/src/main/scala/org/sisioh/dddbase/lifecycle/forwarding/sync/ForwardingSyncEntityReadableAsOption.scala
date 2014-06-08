/*
 * Copyright 2011-2013 Sisioh Project and others. (http://www.sisioh.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.sisioh.dddbase.lifecycle.forwarding.sync

import org.sisioh.dddbase.core.lifecycle.EntityIOContext
import org.sisioh.dddbase.core.lifecycle.sync.{SyncEntityReadableAsOption, SyncEntityReader}
import org.sisioh.dddbase.core.model.{Entity, Identifier}
import scala.util.Try

/**
 * [[org.sisioh.dddbase.core.lifecycle.sync.SyncEntityReadableAsOption]]のデコレータ。
 *
 * @tparam ID 識別子の型
 * @tparam E エンティティの型
 */
trait ForwardingSyncEntityReadableAsOption[ID <: Identifier[_], E <: Entity[ID]]
  extends SyncEntityReadableAsOption[ID, E] {
  this: SyncEntityReader[ID, E] =>

  type Delegate <: SyncEntityReadableAsOption[ID, E]

  /**
   * デリゲート。
   */
  protected val delegate: Delegate

  def resolveAsOptionBy(identifier: ID)
                       (implicit ctx: EntityIOContext[Try]): Option[E] = delegate.resolveAsOptionBy(identifier)

}