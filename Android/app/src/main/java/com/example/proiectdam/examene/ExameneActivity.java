package com.example.proiectdam.examene;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectdam.Database.model.Assignment;
import com.example.proiectdam.Database.service.AssignmentService;
import com.example.proiectdam.R;
import com.example.proiectdam.informatii.asyncTask.Callback;

import java.util.ArrayList;
import java.util.List;

public class ExameneActivity extends AppCompatActivity {

    private static final int ADD_ASSIGNMENT_REQUEST_CODE = 201;
    private static final int UPDATE_ASSIGNMENT_REQUEST_CODE = 202;
    private ListView lvAssignments;
    private Button fab;

    private List<Assignment> assignmentsLista = new ArrayList<>();
    private AssignmentService assignmentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examene_layout);

        Toast.makeText(this, R.string.room, Toast.LENGTH_SHORT).show();

        fab = findViewById(R.id.btnAdd);
        lvAssignments = findViewById(R.id.lv_examene);
        addAssignmentsAdapter();
        fab.setOnClickListener(addNewAssignmentsEventListener());
        lvAssignments.setOnItemClickListener(updateEventListener());
        lvAssignments.setOnItemLongClickListener(deleteEventListener());



        assignmentService = new AssignmentService(getApplicationContext());
        assignmentService.getAll(getAllFromDbCallback());

    }






    private AdapterView.OnItemClickListener updateEventListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AdaugareAssignment.class);
                intent.putExtra(AdaugareAssignment.ASSIGNMENT_KEY, assignmentsLista.get(position));
                startActivityForResult(intent, UPDATE_ASSIGNMENT_REQUEST_CODE);
            }
        };
    }

    private AdapterView.OnItemLongClickListener deleteEventListener() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                assignmentService.delete(deleteToDbCallback(position), assignmentsLista.get(position));
                return true;
            }
        };
    }


    private Callback<Integer> deleteToDbCallback(final int position) {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUiThread(Integer result) {
                if (result != -1) {
                    assignmentsLista.remove(position);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Assignment> updateToDbCallback() {
        return new Callback<Assignment>() {
            @Override
            public void runResultOnUiThread(Assignment result) {
                if (result != null) {
                    for (Assignment as : assignmentsLista) {
                        if (as.getId() == result.getId()) {
                            as.setDescriere(result.getDescriere());
                            as.setData(result.getData());
                            break;
                        }
                    }
                    notifyAdapter();
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Assignment as = (Assignment) data
                    .getSerializableExtra(AdaugareAssignment.ASSIGNMENT_KEY);
            if (requestCode == ADD_ASSIGNMENT_REQUEST_CODE) {
                assignmentService.insert(insertIntoDbCallBack(), as);
            }
            else if (requestCode == UPDATE_ASSIGNMENT_REQUEST_CODE) {
                assignmentService.update(updateToDbCallback(), as);
            }
        }
    }

    private Callback<List<Assignment>> getAllFromDbCallback() {
        return new Callback<List<Assignment>>() {
            @Override
            public void runResultOnUiThread(List<Assignment> result) {
                if (result != null) {
                    assignmentsLista.clear();
                    assignmentsLista.addAll(result);
                    notifyAdapter();
                }

            }
        };
    }

    private Callback<Assignment> insertIntoDbCallBack() {
        return new Callback<Assignment>() {
            @Override
            public void runResultOnUiThread(Assignment result) {
                if (result != null) {
                    assignmentsLista.add(result);
                    notifyAdapter();
                }
            }
        };
    }


    private View.OnClickListener addNewAssignmentsEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AdaugareAssignment.class);
                startActivityForResult(intent, ADD_ASSIGNMENT_REQUEST_CODE);
            }
        };
    }

    private void addAssignmentsAdapter() {

        AssignmentAdapter adapter = new AssignmentAdapter(getApplicationContext(), R.layout.lv_examene, assignmentsLista, getLayoutInflater());
        lvAssignments.setAdapter(adapter);
    }
    private void notifyAdapter() {

        AssignmentAdapter adapter = (AssignmentAdapter) lvAssignments.getAdapter();
        adapter.notifyDataSetChanged();
    }




}
