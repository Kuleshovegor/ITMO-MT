import guru.nidi.graphviz.attribute.Label
import guru.nidi.graphviz.engine.Format
import guru.nidi.graphviz.engine.Graphviz
import guru.nidi.graphviz.model.Factory.*
import guru.nidi.graphviz.model.MutableGraph
import java.io.File

data class Tree(private val value: String, private val children: List<Tree> = listOf()) {

    private fun getGraphviz(mutableGraph: MutableGraph, nowCount: Int = 0): Int {
        val node = mutNode(nowCount.toString()).add(Label.lines(value))
        var count = nowCount

        for (child in children) {
            count++
            mutableGraph.add(
                node.addLink(mutNode((count).toString()).add(Label.lines(child.value)))
            )
            count = child.getGraphviz(mutableGraph, count)
        }

        return count
    }

    fun writeGraphviz(src: String) {
        val g = mutGraph("tmp").setDirected(true)

        getGraphviz(g)

        Graphviz.fromGraph(g).render(Format.PNG).toFile(File(src))
    }

    override fun toString(): String {

        val stringBuilder: StringBuilder = if (value !in listOf("S", "E", "T", "P", "S'", "E'", "T'", "eps", "END", "M", "H", "H'")) {
            StringBuilder(value)
        } else {
            StringBuilder()
        }

        for (child in children) {
            stringBuilder.append(child.toString())
        }

        return stringBuilder.toString()
    }
}
