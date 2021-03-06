package app.shosetsu.common.domain.repositories.base

import app.shosetsu.common.domain.model.local.AppUpdateEntity
import app.shosetsu.common.dto.HResult
import kotlinx.coroutines.flow.Flow

/*
 * This file is part of shosetsu.
 *
 * shosetsu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * shosetsu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with shosetsu.  If not, see <https://www.gnu.org/licenses/>.
 */

/**
 * shosetsu
 * 06 / 09 / 2020
 *
 * Source of truth for all app updates
 */
interface IAppUpdatesRepository {
	/**
	 * Flow of app updates
	 *
	 * Will only be a Success if a version is found higher then the current
	 *
	 * @return
	 * [HResult.Success] There is an update
	 *
	 * [HResult.Error] Something went wrong loading updates
	 *
	 * [HResult.Empty] No updates found
	 *
	 * [HResult.Loading] Initial value
	 */
	fun loadAppUpdateFlow(): Flow<HResult<AppUpdateEntity>>

	/**
	 * Load an app update if present
	 *
	 * @return
	 * [HResult.Empty] if no app updates are present
	 *
	 * [HResult.Success] if an app update is present
	 */
	suspend fun loadRemoteUpdate(): HResult<AppUpdateEntity>

	/**
	 * Load an app update if present
	 *
	 * @return
	 * [HResult.Empty] if no app updates are present
	 *
	 * [HResult.Success] if an app update is present
	 */
	suspend fun loadAppUpdate(): HResult<AppUpdateEntity>

	/**
	 * Can the app self update itself
	 *
	 * @return
	 * [HResult.Success] boolean true if the app can self update, false otherwise
	 *
	 * [HResult.Error] something went wrong
	 *
	 * [HResult.Empty] never
	 *
	 * [HResult.Loading] never
	 */
	fun canSelfUpdate(): HResult<Boolean>

	/**
	 * Downloads the app update specified by [appUpdateEntity]
	 *
	 * @return Path of the apk file, this is messy but it must be done so the intent can work
	 */
	suspend fun downloadAppUpdate(appUpdateEntity: AppUpdateEntity): HResult<String>
}