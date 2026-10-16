package work.socialhub.kmixi2web.entity

import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import kotlin.js.JsExport

/**
 * Poll attached to a post (`Post.poll`, field 40).
 *
 * Until the reader votes, [canViewResults] is false and the server reports
 * zero vote counts. After voting (or for the post author, or once the poll is
 * closed) the counts and percentages are filled in.
 */
@Serializable
@JsExport
class Poll(
    @ProtoNumber(1)
    var pollId: String = "",
    @ProtoNumber(2)
    var options: List<PollOption> = emptyList(),
    @ProtoNumber(3)
    var totalVotes: Long = 0,
    @ProtoNumber(4)
    var expiresAt: Timestamp? = null,
    @ProtoNumber(5)
    var isClosed: Boolean = false,
    @ProtoNumber(6)
    var canViewResults: Boolean = false,
    /** Index of the option the reader voted for, or null if not voted yet. */
    @ProtoNumber(7)
    var votedOptionIndex: Int? = null,
)

@Serializable
@JsExport
class PollOption(
    @ProtoNumber(1)
    var index: Int = 0,
    @ProtoNumber(2)
    var text: String = "",
    @ProtoNumber(3)
    var voteCount: Long = 0,
    /** Share of [Poll.totalVotes] in percent (0 to 100). */
    @ProtoNumber(4)
    var percentage: Float = 0f,
)
