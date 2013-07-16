package org.sisioh.dddbase.core.lifecycle.memory.sync

import org.sisioh.dddbase.core.lifecycle.EntitiesChunk
import org.sisioh.dddbase.core.lifecycle.sync._
import org.sisioh.dddbase.core.model._
import scala.util._

/**
  * [[org.sisioh.dddbase.core.lifecycle.memory.sync.SyncRepositoryOnMemorySupport]]に
  * [[org.sisioh.dddbase.core.lifecycle.EntitiesChunk]]ための機能を追加するトレイト。
  *
  * @tparam R 当該リポジトリを実装する派生型
  * @tparam ID エンティティの識別子の型
  * @tparam T エンティティの型
  */
trait SyncRepositoryOnMemorySupportByChunk
[+R <: SyncRepository[_, ID, T],
 ID <: Identity[_],
 T <: Entity[ID] with EntityCloneable[ID, T] with Ordered[T]]
  extends SyncRepositoryOnMemorySupport[R, ID, T] with SyncEntityReaderByChunk[ID, T] {

     def resolveChunk(index: Int, maxEntities: Int): Try[EntitiesChunk[ID, T]] = {
       val subEntities = toList.slice(index * maxEntities, index * maxEntities + maxEntities)
       Success(EntitiesChunk(index, subEntities))
     }

   }