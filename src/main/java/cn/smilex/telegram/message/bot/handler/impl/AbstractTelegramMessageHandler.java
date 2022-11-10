package cn.smilex.telegram.message.bot.handler.impl;

import cn.smilex.telegram.message.bot.handler.TelegramMessageHandler;
import lombok.Data;

/**
 * @author smilex
 * @date 2022/11/9/21:47
 * @since 1.0
 */
@Data
public abstract class AbstractTelegramMessageHandler<T> implements TelegramMessageHandler<T> {
    private final Node<T> first;
    private Node<T> node;

    public AbstractTelegramMessageHandler() {
        this.first = new Node<>(null, null, null);
        init();
    }

    public abstract void init();

    public AbstractTelegramMessageHandler<T> addToFirst(AbstractTelegramMessageHandler<T> handler) {
        Node<T> oldNode = this.node;
        this.node = new Node<T>(this.first, handler, oldNode);
        this.first.setNext(this.node);

        return this;
    }

    public AbstractTelegramMessageHandler<T> addToLast(AbstractTelegramMessageHandler<T> handler) {
        if (this.node == null) {
            this.node = new Node<>(this.first, handler, null);
            this.first.setNext(this.node);
        } else {
            Node<T> node = this.node;
            while (true) {
                if (node.getNext() == null) {
                    break;
                } else {
                    node = node.getNext();
                }
            }

            node.setNext(new Node<>(node, handler, null));
        }

        return this;
    }

    @Data
    public static class Node<T> {
        private Node<T> pre;
        private AbstractTelegramMessageHandler<T> handler;
        private Node<T> next;

        public Node(Node<T> pre, AbstractTelegramMessageHandler<T> handler, Node<T> next) {
            this.pre = pre;
            this.handler = handler;
            this.next = next;
        }
    }
}