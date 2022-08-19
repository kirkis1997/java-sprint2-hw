package manager;

import manager.interfaces.HistoryManager;
import tasks.Task;
import utility.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {


    private HashMap<Integer, Node<Task>> historyLinks;// HashMap для хранения связи id задачи и объекта Node
    private HandMadeLinkedList<Task> historyList;// List для хранения истории просмотров

    public InMemoryHistoryManager() {
        this.historyLinks = new HashMap<>();
        this.historyList = new HandMadeLinkedList<>();
    }


    @Override
    public void add(Task task) {//Добавить новый элемент в лист просмотров
        if (historyLinks.containsKey(task.getUniqueId())) {//Проверить задачу в HashMap
            remove(task.getUniqueId());//Удалить Node из двусвязного списка
        }
        historyList.linkLast(task);
        historyLinks.put(task.getUniqueId(), historyList.tail);
    }

    @Override
    public void remove(int id) {//Удалить элемент из листа просмотров
        Node<Task> node = historyLinks.get(id);

        if (node != null) {
            historyList.removeNode(node);
            historyLinks.remove(id);
        }

    }

    @Override
    public List<Task> getHistory() {//Получить историю просмотров
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

        public void removeNode(Node<T> element) {// Удалить узел двусвязного списка
            Node<T> next = element.next;
            Node<T> prev = element.prev;

            if (element == head) {
                head = element.next;
            } else {
                element.prev.next = element.next;
            }

            if (element == tail) {
                tail = element.prev;
            } else {
                element.next.prev = element.prev;
            }

            element.data = null;

            size--;

        }

        public Node<T> linkLast(T element) {//Добавить элемент в хвост списка

            Node<T> oldTail = tail;
            Node<T> newNode = new Node<T>(oldTail, element, null);
            tail = newNode;

            if (historyLinks.isEmpty()) {//Если список пуст, для head присвоить значение нового элемента
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

        public boolean isEmpty() {
            return size == 0;
        }

    }
}


