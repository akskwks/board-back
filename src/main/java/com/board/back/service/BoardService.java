package com.board.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.back.entity.Board;
import com.board.back.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
    private BoardRepository boardRepository;

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board saveBoard(Board board) {
    	try {
            return boardRepository.save(board);
        } catch (Exception e) {
            e.printStackTrace();  // 예외 발생 시 오류 메시지 출력
            return null;
        }
    }
    
    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }
    
    
    public Optional<Board> updateBoard(Long id, Board updatedBoard) {
        return boardRepository.findById(id).map(board -> {
            board.setTitle(updatedBoard.getTitle());
            board.setContent(updatedBoard.getContent());
            return boardRepository.save(board);
        });
    }

    public boolean deleteBoard(Long id) {
        if (boardRepository.existsById(id)) {
            boardRepository.deleteById(id);
            return true;
        }
        return false;
    }

}