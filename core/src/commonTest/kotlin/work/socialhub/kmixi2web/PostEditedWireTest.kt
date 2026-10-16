package work.socialhub.kmixi2web

import work.socialhub.kmixi2web.api.response.GetPostResponse
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PostEditedWireTest {

    @Test
    fun postDecodesEditedFlag() {
        // field 36 = true (tag 36 << 3 | varint = 0x120 -> 0xA0 0x02)
        val decoded = wireProto.decodeFromByteArray(
            GetPostResponse.serializer(),
            bytes(
                0x0A, 0x06,
                0x0A, 0x01, 0x70,
                0xA0, 0x02, 0x01,
            ),
        )

        val post = decoded.post
        assertEquals("p", post?.postId)
        assertTrue(post?.isEdited == true)
    }

    @Test
    fun postWithoutEditedFlagIsNotEdited() {
        val decoded = wireProto.decodeFromByteArray(
            GetPostResponse.serializer(),
            bytes(
                0x0A, 0x03,
                0x0A, 0x01, 0x70,
            ),
        )

        val post = decoded.post
        assertEquals("p", post?.postId)
        assertFalse(post?.isEdited == true)
    }
}
