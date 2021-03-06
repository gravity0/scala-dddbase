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
package org.sisioh.dddbase.lifecycle.memory.sync

import org.sisioh.dddbase.core.lifecycle.EntitiesChunk
import org.sisioh.dddbase.core.lifecycle.sync._
import org.sisioh.dddbase.core.model._
import scala.util._

/**
 * [[org.sisioh.dddbase.lifecycle.memory.sync.SyncRepositoryOnMemorySupport]]に
 * [[org.sisioh.dddbase.core.lifecycle.EntitiesChunk]]ための機能を追加するトレイト。
 *
 * @tparam ID エンティティの識別子の型
 * @tparam E エンティティの型
 */
trait SyncRepositoryOnMemorySupportAsChunk
[ID <: Identifier[_],
E <: Entity[ID] with EntityCloneable[ID, E] with Ordered[E]]
  extends SyncEntityReadableAsChunk[ID, E] {
  this: SyncRepositoryOnMemory[ID, E] =>

  def resolveAsChunk(index: Int, maxEntities: Int)(implicit ctx: Ctx): Try[EntitiesChunk[ID, E]] = {
    val subEntities = toList.slice(index * maxEntities, index * maxEntities + maxEntities)
    Success(EntitiesChunk(index, subEntities))
  }

}
