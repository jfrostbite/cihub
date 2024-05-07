import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

@TableName("industry")
data class Industry(
    @TableId(value = "id", type = IdType.AUTO)
    val id: Long? = null, // 行业id
    val name: String, // 行业名
    @TableField("icon")
    val icon: String // 行业图标路径
)
