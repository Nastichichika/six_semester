package main
import "sync"

type ArrayStore struct {
	store []interface{}
}

func NewArrayStore(size uint64) *ArrayStore {
	return &ArrayStore{
		store: make([]interface{}, size),
	}
}

func (s *ArrayStore) Set(value interface{}, pos uint64) {
	s.store[pos] = value
}

func (s *ArrayStore) Get(pos uint64) interface{} {
	return s.store[pos]
}

func (s *ArrayStore) Remove(pos uint64) interface{} {
	var item = s.store[pos]
	s.store[pos] = nil
	return item
}

func (s ArrayStore) Size() uint64 {
	return uint64(len(s.store))
}

type BlockingQueue struct {
	lock:     lock,
	notEmpty: sync.NewCond(lock),
	notFull:  sync.NewCond(lock),
	count:    uint64(0),
	store:    NewArrayStore(capacity),
}

func NewArrayBlockingQueue(capacity uint64) (*BlockingQueue, error) {
	if capacity < 1 {
		return nil, ErrorCapacity
	}

	lock := new(sync.Mutex)

	return &BlockingQueue{
		lock:     lock,
		notEmpty: sync.NewCond(lock),
		notFull:  sync.NewCond(lock),
		count:    uint64(0),
		store:    NewArrayStore(capacity),
	}, nil
}
