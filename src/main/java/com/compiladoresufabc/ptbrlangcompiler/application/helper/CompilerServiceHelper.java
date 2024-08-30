package com.compiladoresufabc.ptbrlangcompiler.application.helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.compiladoresufabc.ptbrlangcompiler.commons.antlr.PtBrLangGrammarLexer;
import com.compiladoresufabc.ptbrlangcompiler.commons.antlr.PtBrLangGrammarParser;
import com.compiladoresufabc.ptbrlangcompiler.commons.generator.Program;

@Component
public class CompilerServiceHelper {

	@Value("${compiler.output-directory}")
	private String outputDirectory;

	public ResponseEntity<?> processJavaFile(MultipartFile file) {
		return processFile(file, ".java");
	}

	public ResponseEntity<?> processCFile(MultipartFile file) {
		return processFile(file, ".c");
	}

	public ResponseEntity<?> processPythonFile(MultipartFile file) {
		return processFile(file, ".py");
	}

	private ResponseEntity<?> processFile(MultipartFile file, String outputExtension) {
		File outputFile = null;

		try {
			outputFile = createOutputFile(file.getOriginalFilename(), outputExtension);
			processFileWithANTLR(file, outputFile);
			return sendFileAsResponse(outputFile);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Erro ao processar o arquivo: " + e.getMessage());
		}
	}

	private File createOutputFile(String fileName, String outputExtension) throws IOException {
		String originalNameWithoutExt = fileName.replaceFirst("[.][^.]+$", "");
		File outputFile = new File(outputDirectory, originalNameWithoutExt + outputExtension);
		if (!outputFile.exists()) {
			outputFile.createNewFile();
		}
		return outputFile;
	}

	// Retornando apenas o que veio em caso de sucesso, se não exception específica
	private void processFileWithANTLR(MultipartFile originalFile, File outputFile) throws IOException {
		try {
			PtBrLangGrammarLexer lexer = new PtBrLangGrammarLexer(
					CharStreams.fromFileName(outputDirectory + originalFile.getOriginalFilename()));
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			PtBrLangGrammarParser parser = new PtBrLangGrammarParser(tokenStream);

			parser.programa();
			
			Program program = parser.getProgram();
			
			System.out.println(program.generateCode());
			
			FileWriter fW = new FileWriter(outputFile);
			PrintWriter pW = new PrintWriter(fW);
			pW.println(program.generateCode());
			pW.close();
		} catch (RecognitionException e) {
			throw new IOException("Erro de análise léxica ou sintática: " + e.getMessage());
		} catch (IOException e) {
			throw new IOException("Erro ao processar o arquivo: " + e.getMessage());
		}
	}

	private ResponseEntity<?> sendFileAsResponse(File outputFile) throws IOException {
		FileSystemResource resource = new FileSystemResource(outputFile);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + outputFile.getName());
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentLength(resource.contentLength());

		return ResponseEntity.ok().headers(headers).body(resource);
	}
}
