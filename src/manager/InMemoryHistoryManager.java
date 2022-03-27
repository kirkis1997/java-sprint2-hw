package manager;

import manager.interfaces.HistoryManager;
import tasks.Task;
import utility.Node;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {


    private HashMap<Integer, Node<Task>> historyLinks;// HashMap для хранения связи id задачи и объекта Node
    private HandMadeLinkedList<Task> historyList;// List для хранения истории просмотров

    public InMemoryHistoryManager() {
        this.historyLinks = new HashMap<>();
        this.historyList = new HandMadeLinkedList<>();
    }

    @Override
    public void remove(int id) {
        Node<Task> node = historyLinks.get(id);

        if (node != null) {
            historyList.removeNode(node);
        }
        historyLinks.remove(id);
    }

    @Override
    public void add(Task task) {//Добавить новый элемент в лист просмотров
        if (historyLinks.containsKey(task.getUniqueId())) {
            remove(task.getUniqueId());
        }
        historyLinks.put(task.getUniqueId(), historyList.linkLast(task));
    }

    @Override
    public List<Task> getHistory() {
        return historyList.getTasks();
    }

    /**
     * Создать собственный двусвязный список
     * в соответствии с ТЗ
     */
    public class HandMadeLinkedList<T> {

        private Node<T> head;//Головной элемент списка
        private Node<T> tail;//Хвостовой элемент списка
        private int size = 0;//Размер списка

        public void removeNode(Node<T> element) {// Уалить узел связного списка
            Node<T> next = element.next;
            Node<T> prev = element.prev;

            if (prev == null) {
                head = next;
            }
            else if (next == null) {
                tail = prev;
            }

            element.data = null;
            size--;

        }

        public Node<T> linkLast(T element) {//Добавить элемент в хвост списка
            Node<T> oldTail = tail;
            Node<T> newNode = new Node<T>(oldTail, (Node<T>) element, null);
            tail = newNode;

            if (oldTail == null) {
                head = newNode;
            } else {
                oldTail.next = newNode;
            }
            size++;
            return newNode;
        }

        public List<T> getTasks() {//Получить список просмотренных задач
            List<T> tasksList = new ArrayList<>();
            Node<T> pointer = head;

            while (pointer != null && size > 0) {
                tasksList.add(pointer.data);
                pointer = pointer.next;
            }

            return tasksList;

        }
    }
}


