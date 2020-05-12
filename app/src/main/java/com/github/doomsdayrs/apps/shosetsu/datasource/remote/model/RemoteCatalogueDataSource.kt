package com.github.doomsdayrs.apps.shosetsu.datasource.remote.model

import android.util.Log
import app.shosetsu.lib.Formatter
import app.shosetsu.lib.Novel
import com.github.doomsdayrs.apps.shosetsu.common.consts.ErrorKeys.ERROR_GENERAL
import com.github.doomsdayrs.apps.shosetsu.common.consts.ErrorKeys.ERROR_LUA
import com.github.doomsdayrs.apps.shosetsu.common.consts.ErrorKeys.ERROR_NETWORK
import com.github.doomsdayrs.apps.shosetsu.common.dto.HResult
import com.github.doomsdayrs.apps.shosetsu.common.dto.errorResult
import com.github.doomsdayrs.apps.shosetsu.common.dto.successResult
import com.github.doomsdayrs.apps.shosetsu.common.ext.logID
import com.github.doomsdayrs.apps.shosetsu.datasource.remote.base.IRemoteCatalogueDataSource
import okio.IOException
import org.luaj.vm2.LuaError

/*
 * This file is part of Shosetsu.
 *
 * Shosetsu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Shosetsu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Shosetsu.  If not, see <https://www.gnu.org/licenses/>.
 */


/**
 * shosetsu
 * 10 / May / 2020
 */
class RemoteCatalogueDataSource : IRemoteCatalogueDataSource {
	override suspend fun search(
			formatter: Formatter,
			query: String,
			data: Array<Any>
	): HResult<List<Novel.Listing>> {
		return try {
			successResult(
					formatter.search(ArrayList<Any>(arrayListOf(query))
							.plus(data).toTypedArray()
					) {
						Log.i(logID(), it)
					}.toList()
			)
		} catch (e: IOException) {
			errorResult(ERROR_NETWORK, e.message ?: "Unknown Network Exception")
		} catch (e: LuaError) {
			errorResult(ERROR_LUA, e.message ?: "Unknown Lua Error")
		} catch (e: Exception) {
			errorResult(ERROR_GENERAL, e.message ?: "Unknown General Error")
		}
	}
}

