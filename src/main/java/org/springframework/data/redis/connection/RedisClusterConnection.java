/*
 * Copyright 2015-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.redis.connection;

import java.util.Set;

import org.springframework.lang.Nullable;

/**
 * {@link RedisClusterConnection} allows sending commands to dedicated nodes within the cluster. A
 * {@link RedisClusterNode} can be obtained from {@link #clusterGetNodes()} or it can be constructed using either
 * {@link RedisClusterNode#getHost() host} and {@link RedisClusterNode#getPort()} or the {@link RedisClusterNode#getId()
 * node Id}.
 *
 * @author Christoph Strobl
 * @author Mark Paluch
 * @since 1.7
 */
public interface RedisClusterConnection extends RedisConnection, RedisClusterCommands, RedisClusterServerCommands {

	/**
	 * @param node must not be {@literal null}.
	 * @return {@literal null} when used in pipeline / transaction.
	 * @see RedisConnectionCommands#ping()
	 */
	@Nullable
	String ping(RedisClusterNode node);

	/**
	 * @param node must not be {@literal null}.
	 * @param pattern must not be {@literal null}.
	 * @return {@literal null} when used in pipeline / transaction.
	 * @see RedisKeyCommands#keys(byte[])
	 */
	@Nullable
	Set<byte[]> keys(RedisClusterNode node, byte[] pattern);

	/**
	 * @param node must not be {@literal null}.
	 * @return {@literal null} when no keys stored at node or when used in pipeline / transaction.
	 * @see RedisKeyCommands#randomKey()
	 */
	@Nullable
	byte[] randomKey(RedisClusterNode node);

	/**
	 * Get {@link RedisClusterServerCommands}.
	 *
	 * @return never {@literal null}.
	 * @since 2.0
	 */
	default RedisClusterServerCommands serverCommands() {
		return this;
	}
}
