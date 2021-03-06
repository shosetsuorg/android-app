package app.shosetsu.common.consts.settings

import app.shosetsu.common.domain.model.local.LibrarySortFilterEntity
import app.shosetsu.common.enums.MarkingType.ONVIEW
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*

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
 * 23 / 06 / 2020
 */


typealias IntKey = SettingKey<Int>
typealias BooleanKey = SettingKey<Boolean>
typealias FloatKey = SettingKey<Float>
typealias StringKey = SettingKey<String>
typealias StringSetKey = SettingKey<Set<String>>


sealed class SettingKey<T : Any>(val name: String, val default: T) {

	object ReaderTheme : IntKey("readerTheme", -1)

	object FirstTime : BooleanKey("first_time", true)


	// How things look in Reader
	object ReaderUserThemes : StringSetKey("readerThemes", setOf())


	object ReaderTextSize : FloatKey("readerTextSize", 14f)

	//TODO turn into float
	object ReaderParagraphSpacing : FloatKey("readerParagraphSpacing", 1f)
	object ReaderIndentSize : IntKey("readerIndentSize", 1)

	/**
	 * 0 : align start
	 * 1 : align center
	 * 2 : justified
	 * 3 : align end
	 */
	object ReaderTextAlignment : IntKey("readerTextAlignment", 0)

	object ReaderMarginSize : FloatKey("readerMargin", 0f)

	object ReaderLineSpacing : FloatKey("readerLineSpacing", 1f)

	object ReaderFont : StringKey("readerFont", "")

	object ReaderType : IntKey("readerType", -1)

	//- How things act in Reader
	object ReaderIsTapToScroll : BooleanKey("tapToScroll", false)
	object ReaderVolumeScroll : BooleanKey("volumeToScroll", false)


	object ReaderIsInvertedSwipe : BooleanKey("invertedSwipe", false)
	object ReadingMarkingType : StringKey("readingMarkingType", ONVIEW.name)

	/**
	 * Should the application convert string returns from an extension to an Html page
	 */
	object ReaderStringToHtml : BooleanKey("convertStringToHtml", false)

	/**
	 * User customization for CSS in html reader
	 */
	object ReaderHtmlCss : StringKey("readerHtmlCss", "")

	/**
	 * Instead of vertically moving between chapters, do a horizontal move
	 */
	object ReaderHorizontalPageSwap : BooleanKey("readerHorizontalPaging", false)

	/**
	 * The reader smoothly moves between chapters
	 */
	object ReaderContinuousScroll : BooleanKey("readerContinuousScroll", false)


	//- Some things
	object ChaptersResumeFirstUnread : BooleanKey(
		"readerResumeFirstUnread",
		false
	)

	// Download options
	object IsDownloadPaused : BooleanKey("isDownloadPaused", false)


	/**
	 * Which chapter to delete after reading
	 * If -1, then does nothing
	 * If 0, then deletes the read chapter
	 * If 1+, deletes the chapter of READ CHAPTER - [deletePreviousChapter]
	 */
	object DeleteReadChapter : IntKey("deleteReadChapter", -1)

	object DownloadOnLowStorage : BooleanKey("downloadNotLowStorage", false)
	object DownloadOnLowBattery : BooleanKey("downloadNotLowBattery", false)
	object DownloadOnMeteredConnection : BooleanKey(
		"downloadNotMetered",
		false
	)

	object DownloadOnlyWhenIdle : BooleanKey("downloadIdle", false)

	/** Bookmark a novel if it is not bookmarked when a chapter from it is downloaded */
	object BookmarkOnDownload : BooleanKey("bookmarkOnDownload", false)

	// Update options
	object IsDownloadOnUpdate : BooleanKey("isDownloadOnUpdate", false)
	object OnlyUpdateOngoing : BooleanKey("onlyUpdateOngoing", false)
	object UpdateOnStartup : BooleanKey("updateOnStartup", true)
	object UpdateCycle : IntKey("updateCycle", 1)
	object UpdateOnLowStorage : BooleanKey("updateLowStorage", false)
	object UpdateOnLowBattery : BooleanKey("updateLowBattery", false)
	object UpdateOnMeteredConnection : BooleanKey("updateMetered", false)
	object UpdateOnlyWhenIdle : BooleanKey("updateIdle", false)
	object UpdateNotificationStyle : BooleanKey("updateNotificationStyle", false)


	// App Update Options
	object AppUpdateOnStartup : BooleanKey("appUpdateOnStartup", true)
	object AppUpdateOnMeteredConnection : BooleanKey(
		"appUpdateMetered",
		false
	)

	object AppUpdateOnlyWhenIdle : BooleanKey("appUpdateIdle", false)
	object AppUpdateCycle : IntKey("appUpdateCycle", 1)


	// View options
	object ChapterColumnsInPortait : IntKey("columnsInNovelsViewP", 3)
	object ChapterColumnsInLandscape : IntKey("columnsInNovelsViewH", 6)
	object SelectedNovelCardType : IntKey("novelCardType", 0)
	object NavStyle : IntKey("navigationStyle", 0)

	// Backup Options
	object BackupChapters : BooleanKey("backupChapters", true)
	object BackupSettings : BooleanKey("backupSettings", false)
	object BackupCycle : IntKey("backupCycle", 3)

	object BackupOnLowStorage : BooleanKey("backupLowStorage", false)
	object BackupOnLowBattery : BooleanKey("backupLowBattery", false)
	object BackupOnlyWhenIdle : BooleanKey("backupIdle", false)

	// Download Options
	object CustomExportDirectory : StringKey("downloadDirectory", "")

	/** How many threads to download at the same time */
	object DownloadThreadPool : IntKey("downloadThreads", 1)

	/** How many extension threads allowed to work in the pool */
	object DownloadExtThreads : IntKey("downloadExtThreads", 1)

	/** If the reader can mark a read chapter as reading when its opened / scrolled */
	object ReaderMarkReadAsReading : BooleanKey(
		"readerMarkReadAsReading",
		false
	)

	object AppTheme : IntKey("selectedAppTheme", 0)

	object LibraryFilter :
		StringKey("libraryFilter", Json { }.encodeToString(LibrarySortFilterEntity()))

	companion object {
		private val map: Map<String, SettingKey<*>> by lazy {
			SettingKey::class.sealedSubclasses
				.map { it.objectInstance }
				.filterIsInstance<SettingKey<*>>()
				.associateBy { it.name }
		}

		fun valueOf(key: String): SettingKey<*> =
			map[key] ?: error("SettingKey: Cannot find $key")
	}
}